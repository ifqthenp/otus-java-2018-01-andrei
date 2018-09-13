package com.otus.hw_16.front.websocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MsgWebSocketHandler {

    private final Set<MsgWebSocket> sockets;

    public MsgWebSocketHandler() {
        this.sockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendToWebSockets(String userDataSet) {
        for (MsgWebSocket websocket : sockets) {
            websocket.sendString(userDataSet);
        }
    }

    public void add(MsgWebSocket webSocket) {
        sockets.add(webSocket);
    }

    public void remove(MsgWebSocket webSocket) {
        sockets.remove(webSocket);
    }

}
