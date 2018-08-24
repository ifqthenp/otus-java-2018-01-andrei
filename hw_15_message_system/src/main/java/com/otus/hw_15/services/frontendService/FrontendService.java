package com.otus.hw_15.services.frontendService;

import com.otus.hw_15.entities.dataset.UserDataSet;
import com.otus.hw_15.messageSystem.Addressee;
import com.otus.hw_15.services.frontendService.websocket.MsgWebSocketHandler;

public interface FrontendService extends Addressee {

    void init();

    void handleRequest(long id);

    void addUserDataSet(long id, UserDataSet userDataSet);

    MsgWebSocketHandler getMsgWebSocketHandler();
}
