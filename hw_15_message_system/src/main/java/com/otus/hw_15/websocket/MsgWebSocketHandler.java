package com.otus.hw_15.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.otus.hw_15.entities.dataset.UserDataSet;
import org.hibernate.Hibernate;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MsgWebSocketHandler {

    private final Set<MsgWebSocket> sockets;
    private final Gson gson;

    public MsgWebSocketHandler() {
        this.sockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.gson = new Gson();
    }

    public void sendToWebSockets(UserDataSet userDataSet) {
        UserDataSet user = (UserDataSet) Hibernate.unproxy(userDataSet);

        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        String msg = gsonBuilder.create().toJson(user);

        for (MsgWebSocket websocket : sockets) {
            websocket.sendString(msg);
        }
    }

    public void add(MsgWebSocket webSocket) {
        sockets.add(webSocket);
    }

    public void remove(MsgWebSocket webSocket) {
        sockets.remove(webSocket);
    }
}
