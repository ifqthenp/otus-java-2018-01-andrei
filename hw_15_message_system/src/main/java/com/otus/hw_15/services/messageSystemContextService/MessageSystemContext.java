package com.otus.hw_15.services.messageSystemContextService;

import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.MessageSystem;

public interface MessageSystemContext {

    MessageSystem getMessageSystem();

    Address getFrontAddress();

    void setFrontAddress(Address frontAddress);

    Address getDbAddress();

    void setDbAddress(Address dbAddress);
}
