package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;

public interface FrontendService {

    void init();

    void handleRequestFromWebSocket(String id);

    void handleResponseToWebSocket(String id);

    MsgWebSocketHandler getMsgWebSocketHandler();

}
