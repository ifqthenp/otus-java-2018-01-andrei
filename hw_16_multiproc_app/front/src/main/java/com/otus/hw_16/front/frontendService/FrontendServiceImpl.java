package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.app.MsgWorker;
import com.otus.hw_16.master.messages.PingMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FrontendServiceImpl implements FrontendService {

    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class.getName());

    private MsgWebSocketHandler msgWebSocketHandler;

    public FrontendServiceImpl(final MsgWebSocketHandler msgWebSocketHandler) {
        this.msgWebSocketHandler = msgWebSocketHandler;
    }

    @Override
    public void init() {
//        this.context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void handleRequest(final String id, MsgWorker frontClient) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Msg msg = frontClient.take();
                    System.out.println("Message received: " + msg.toString());
                    PingMsg response = (PingMsg) msg;
                    addUserDataSet("Response to WebSockets: " + response.getMessage());
                }
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        });

        Msg msg = new PingMsg(id);
        frontClient.send(msg);
    }

    @Override
    public void addUserDataSet(final String id) {
        msgWebSocketHandler.sendToWebSockets(id);
    }

    @Override
    public MsgWebSocketHandler getMsgWebSocketHandler() {
        return Objects.requireNonNull(this.msgWebSocketHandler);
    }

}
