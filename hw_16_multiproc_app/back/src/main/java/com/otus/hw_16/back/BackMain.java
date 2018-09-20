package com.otus.hw_16.back;

import com.google.gson.GsonBuilder;
import com.otus.hw_16.back.cacheService.CacheService;
import com.otus.hw_16.back.dbService.DBService;
import com.otus.hw_16.back.entities.UserDataSet;
import com.otus.hw_16.back.util.DatabaseUtil;
import com.otus.hw_16.back.util.MBeansUtil;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.channel.ClientSocketMsgWorker;
import com.otus.hw_16.master.channel.SocketMsgWorker;
import com.otus.hw_16.master.messages.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackMain {

    private static final Logger logger = LoggerFactory.getLogger(BackMain.class.getName());

    private DBService dbService;

    private static final int PORT = 5050;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        DatabaseUtil.emulateLoad(context);

        new BackMain(context).start();
    }

    private BackMain(ApplicationContext context) {
        dbService = context.getBean(DBService.class);
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

                    Msg response = null;

                    if (msg instanceof UserDataByIdRequest) {
                        response = getUserDataByIdResponse(msg);
                        client.send(response);
                    } else if (msg instanceof CacheUpdateRequest) {
                        response = getCacheUpdateResponse();
                        client.send(response);
                    }
                    logger.info("Response message has been sent to frontend: {}", Objects.requireNonNull(response).toString());
                }
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        });

        Msg message = new BackClientPingMsg();
        client.send(message);
    }

    private Msg getCacheUpdateResponse() {
        final Map<String, Object> cacheStats = MBeansUtil.getEhCacheStats(CacheService.USER_DATASET_CACHE_NAME);
        final String cacheStatsJson = convertToJson(cacheStats);
        return new CacheUpdateResponse(cacheStatsJson);
    }

    private Msg getUserDataByIdResponse(final Msg msg) {
        final Long userId = Long.parseLong(((UserDataByIdRequest) msg).getMessage());
        final UserDataSet proxiedUserDataSet = dbService.read(userId);
        final UserDataSet userDataSet = (UserDataSet) Hibernate.unproxy(proxiedUserDataSet);
        final String userDataJson = convertToJson(userDataSet);
        return new UserDataByIdResponse(userDataJson);
    }

    private String convertToJson(final Object o) {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .toJson(o);
    }

}
