package com.otus.hw_15.services.messageSystemContextService;

import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.MessageSystem;

public class MessageSystemContextImpl implements MessageSystemContext {

    private final MessageSystem messageSystem;

    private Address frontAddress;
    private Address dbAddress;

    public MessageSystemContextImpl(final MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    @Override
    public MessageSystem getMessageSystem() {
        return this.messageSystem;
    }

    @Override
    public Address getFrontAddress() {
        return this.frontAddress;
    }

    @Override
    public void setFrontAddress(final Address frontAddress) {
        this.frontAddress = frontAddress;
    }

    @Override
    public Address getDbAddress() {
        return this.dbAddress;
    }

    @Override
    public void setDbAddress(final Address dbAddress) {
        this.dbAddress = dbAddress;
    }
}
