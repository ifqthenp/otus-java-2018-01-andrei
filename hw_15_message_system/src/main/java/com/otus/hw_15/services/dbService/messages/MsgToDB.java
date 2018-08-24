package com.otus.hw_15.services.dbService.messages;

import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.Addressee;
import com.otus.hw_15.messageSystem.Message;
import com.otus.hw_15.services.dbService.DBService;

public abstract class MsgToDB extends Message {

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        } else {
            throw new IllegalStateException("Addressee is not a DB Service");
        }
    }

    public abstract void exec(DBService dbService);
}
