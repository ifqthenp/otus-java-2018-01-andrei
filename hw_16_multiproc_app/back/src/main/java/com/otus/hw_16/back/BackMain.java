package com.otus.hw_16.back;

import com.otus.hw_16.back.util.DatabaseUtil;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.channel.ClientSocketMsgWorker;
import com.otus.hw_16.master.channel.SocketMsgWorker;
import com.otus.hw_16.master.messages.PingMsg;
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
                    Object msg = client.take();
                    System.out.println("Message received: " + msg.toString());
                }
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        });

        int count = 0;
        while (count < MAX_MESSAGES_COUNT) {
            Msg msg = new PingMsg();
            client.send(msg);
            System.out.println("Message sent: " + msg.toString());
            Thread.sleep(PAUSE_MS);
            count++;
        }
        client.close();
        executorService.shutdown();
    }

}
