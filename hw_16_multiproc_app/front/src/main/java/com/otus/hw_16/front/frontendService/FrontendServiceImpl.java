package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.app.MsgWorker;
import com.otus.hw_16.master.messages.UserDataByIdRequest;
import com.otus.hw_16.master.messages.UserDataByIdResponse;
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
    public void handleRequestFromWebSocket(final String id, MsgWorker frontClient) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Msg msg = frontClient.take();
                    logger.info("Message received: {}", msg.toString());
                    UserDataByIdResponse response = (UserDataByIdResponse) msg;
                    handleResponseToWebSocket(response.getMessage());
                }
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        });

        Msg msg = new UserDataByIdRequest(id);
        frontClient.send(msg);
    }

    @Override
    public void handleResponseToWebSocket(final String id) {
        msgWebSocketHandler.sendToWebSockets(id);
    }

    @Override
    public MsgWebSocketHandler getMsgWebSocketHandler() {
        return Objects.requireNonNull(this.msgWebSocketHandler);
    }

}
