package com.otus.hw_15.services.frontendService.messages;

import com.otus.hw_15.entities.dataset.UserDataSet;
import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.services.frontendService.FrontendService;
import com.otus.hw_15.services.frontendService.messages.MsgToFrontend;

public class MsgGetUserByIdAnswer extends MsgToFrontend {

    private final long userId;
    private final UserDataSet userDataSet;

    public MsgGetUserByIdAnswer(Address from, Address to, long userId, UserDataSet userDataSet) {
        super(from, to);
        this.userId = userId;
        this.userDataSet = userDataSet;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.addUserDataSet(userId, userDataSet);
    }
}
