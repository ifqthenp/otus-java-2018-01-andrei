package com.otus.hw_16.front.frontendService;

import com.otus.hw_16.front.websocket.MsgWebSocketHandler;

public interface FrontendService {

    void init();

    void handleRequestFromWebSocket(String message);

    void handleResponseToWebSocket(String message);

    MsgWebSocketHandler getMsgWebSocketHandler();

}
