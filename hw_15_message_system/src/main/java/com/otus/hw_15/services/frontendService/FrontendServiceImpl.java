package com.otus.hw_15.services.frontendService;

import com.otus.hw_15.entities.dataset.UserDataSet;
import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.Message;
import com.otus.hw_15.messageSystem.MessageSystem;
import com.otus.hw_15.messages.MsgGetUserById;
import com.otus.hw_15.websocket.MsgWebSocketHandler;
import com.otus.hw_15.services.messageSystemContextService.MessageSystemContext;

import java.util.Objects;

public class FrontendServiceImpl implements FrontendService {

    private Address address;
    private MessageSystemContext context;
    private MsgWebSocketHandler msgWebSocketHandler;

    public FrontendServiceImpl(final Address address,
                               final MessageSystemContext context,
                               final MsgWebSocketHandler msgWebSocketHandler) {
        this.address = address;
        this.context = context;
        this.msgWebSocketHandler = msgWebSocketHandler;
    }

    @Override
    public void init() {
        this.context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void handleRequest(final long id) {
        Message message = new MsgGetUserById(getAddress(), context.getDbAddress(), id);
        context.getMessageSystem().sendMessage(message);
    }

    @Override
    public void addUserDataSet(final long id, final UserDataSet userDataSet) {
        msgWebSocketHandler.sendToWebSockets(userDataSet);
    }

    @Override
    public MsgWebSocketHandler getMsgWebSocketHandler() {
        return Objects.requireNonNull(this.msgWebSocketHandler);
    }

    @Override
    public Address getAddress() {
        return this.address;
    }

    @Override
    public MessageSystem getMessageSystemFromContext() {
        return this.context.getMessageSystem();
    }
}
