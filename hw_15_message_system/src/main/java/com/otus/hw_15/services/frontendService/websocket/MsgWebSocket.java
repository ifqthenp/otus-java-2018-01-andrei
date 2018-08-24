package com.otus.hw_15.services.frontendService.websocket;

import com.otus.hw_15.services.frontendService.FrontendService;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

@WebSocket
public class MsgWebSocket {

    private final static Logger logger = Logger.getLogger(MsgWebSocket.class.getName());

    private Session session;
    private FrontendService frontendService;

    public MsgWebSocket(final FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @OnWebSocketMessage
    public void onMessage(final String id) {

        frontendService.handleRequest(Long.parseLong(id));

        logger.log(INFO, "Request for user ID " + id + " has been sent");
    }

    @OnWebSocketConnect
    public void onOpen(final Session session) {
        frontendService.getMsgWebSocketHandler().add(this);
        setSession(session);

        logger.log(INFO, "MsgWebSocket has been opened");
    }

    @OnWebSocketClose
    public void onClose(final int statusCode, final String reason) {
        frontendService.getMsgWebSocketHandler().remove(this);
        logger.log(INFO, "MsgWebSocket has been closed. Code: " + statusCode + ". Reason: " + reason);
    }

    private void setSession(final Session session) {
        this.session = session;
    }

    public void sendString(final String msg) {
        try {
            this.session.getRemote().sendString(msg);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
