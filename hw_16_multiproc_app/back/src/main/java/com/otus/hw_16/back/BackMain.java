package com.otus.hw_16.back;

import com.google.gson.GsonBuilder;
import com.otus.hw_16.back.dbService.DBService;
import com.otus.hw_16.back.entities.UserDataSet;
import com.otus.hw_16.back.util.DatabaseUtil;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.channel.ClientSocketMsgWorker;
import com.otus.hw_16.master.channel.SocketMsgWorker;
import com.otus.hw_16.master.messages.BackClientPingMsg;
import com.otus.hw_16.master.messages.UserDataByIdRequest;
import com.otus.hw_16.master.messages.UserDataByIdResponse;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackMain {

    private static final Logger logger = LoggerFactory.getLogger(BackMain.class.getName());

    private static final int PORT = 5050;
    private static final int PAUSE_MS = 5000;
    private static final String HOST = "localhost";
    private static final int MAX_MESSAGES_COUNT = 10;

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        DatabaseUtil.emulateLoad(context);

        new BackMain().start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void start() throws Exception {
        SocketMsgWorker client = new ClientSocketMsgWorker(HOST, PORT);
        client.init();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Msg msg = client.take();
                    logger.info("Message has been received on backend: {}", msg.toString());

                    if (msg instanceof UserDataByIdRequest) {
                        final Long userId = Long.parseLong(((UserDataByIdRequest) msg).getMessage());
                        final UserDataSet proxiedUserDataSet = dbService.read(userId);
                        final UserDataSet userDataSet = (UserDataSet) Hibernate.unproxy(proxiedUserDataSet);
                        final String userDataJson = convertToJson(userDataSet);
                        final UserDataByIdResponse response = new UserDataByIdResponse(userDataJson);
                        client.send(response);
                        logger.info("Response message has been sent to frontend: {}", response.toString());
                    }
                }
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        });

        Msg message = new BackClientPingMsg();
        client.send(message);
    }
    }

}
