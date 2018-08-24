package com.otus.hw_15.services.frontendService.messages;

import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.Addressee;
import com.otus.hw_15.messageSystem.Message;
import com.otus.hw_15.services.frontendService.FrontendService;

public abstract class MsgToFrontend extends Message {

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        } else {
            throw new IllegalStateException("Addressee is not a Frontend Service");
        }
    }

    public abstract void exec(FrontendService frontendService);
}
