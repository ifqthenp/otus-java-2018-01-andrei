package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;
import com.otus.hw_16.master.app.MsgWorker;

public interface FrontendService {

    void init();

    void handleRequest(String id, MsgWorker frontClient);

    void addUserDataSet(String id);

    MsgWebSocketHandler getMsgWebSocketHandler();

}
