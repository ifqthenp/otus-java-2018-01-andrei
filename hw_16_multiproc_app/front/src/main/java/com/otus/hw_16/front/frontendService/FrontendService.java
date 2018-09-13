package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;

public interface FrontendService {

    void init();

    void handleRequest(String id);

    void addUserDataSet(String id);

    MsgWebSocketHandler getMsgWebSocketHandler();

}
