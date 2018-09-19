package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.app.MsgWorker;
import com.otus.hw_16.master.messages.UserDataByIdRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FrontendServiceImpl implements FrontendService {

    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class.getName());

    private MsgWorker frontClient;
    private MsgWebSocketHandler msgWebSocketHandler;

    public FrontendServiceImpl(final MsgWebSocketHandler msgWebSocketHandler, final MsgWorker frontClient) {
        this.frontClient = frontClient;
        this.msgWebSocketHandler = msgWebSocketHandler;
    }

    @Override
    public void handleRequestFromWebSocket(final String id) {
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
