package com.otus.hw_16.front.websocket;

import com.otus.hw_16.front.frontendService.FrontendService;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebSocket
public class MsgWebSocket {

    private final static Logger logger = LoggerFactory.getLogger(MsgWebSocket.class.getName());

    private Session session;
    private FrontendService frontendService;

    public MsgWebSocket(final FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @OnWebSocketMessage
    public void onMessage(final String id) {
        frontendService.handleRequestFromWebSocket(id);
        logger.info("Request for user ID {} has been sent", id);
    }

    @OnWebSocketConnect
    public void onOpen(final Session session) {
        setSession(session);
        frontendService.getMsgWebSocketHandler().add(this);
        logger.info("MsgWebSocket has been opened");
    }

    @OnWebSocketClose
    public void onClose(final int statusCode, final String reason) {
        frontendService.getMsgWebSocketHandler().remove(this);
        logger.info("MsgWebSocket has been closed: code {}, reason {}.", statusCode, reason);
    }

    private void setSession(final Session session) {
        this.session = session;
    }

    public void sendString(final String msg) {
        try {
            this.session.getRemote().sendString(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
