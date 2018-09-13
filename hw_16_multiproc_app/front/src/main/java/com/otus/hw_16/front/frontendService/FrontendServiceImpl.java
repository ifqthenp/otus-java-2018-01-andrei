package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;

import java.util.Objects;

public class FrontendServiceImpl implements FrontendService {

    private MsgWebSocketHandler msgWebSocketHandler;

    public FrontendServiceImpl(final MsgWebSocketHandler msgWebSocketHandler) {
        this.msgWebSocketHandler = msgWebSocketHandler;
    }

    @Override
    public void init() {
//        this.context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void handleRequest(final String id) {
//        Message message = new MsgGetUserById(getAddress(), context.getDbAddress(), id);
//        context.getMessageSystem().sendMessage(message);
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
