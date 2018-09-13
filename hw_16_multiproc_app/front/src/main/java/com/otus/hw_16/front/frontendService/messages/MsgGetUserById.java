//package com.otus.hw_16.front.frontendService.messages;
//
//import com.otus.hw_15.entities.dataset.UserDataSet;
//import com.otus.hw_15.messageSystem.Address;
//import com.otus.hw_15.services.dbService.DBService;
//import com.otus.hw_15.services.dbService.messages.MsgToDB;
//
//public class MsgGetUserById extends MsgToDB {
//
//    private final long userId;
//
//    public MsgGetUserById(Address from, Address to, long userId) {
//        super(from, to);
//        this.userId = userId;
//    }
//
//    @Override
//    public void exec(DBService dbService) {
//        UserDataSet userDataSet = dbService.read(userId);
//        dbService.getMessageSystemFromContext().sendMessage(new MsgGetUserByIdAnswer(getTo(), getFrom(), userId, userDataSet));
//    }
//
//}
