package com.otus.hw_15.services.frontendService.websocket;

import com.otus.hw_15.services.frontendService.FrontendService;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MsgWebSocketCreator implements WebSocketCreator {

    private final static Logger logger = Logger.getLogger(MsgWebSocketCreator.class.getName());

    private FrontendService frontendService;

    public MsgWebSocketCreator(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Object createWebSocket(final ServletUpgradeRequest servletUpgradeRequest, final ServletUpgradeResponse servletUpgradeResponse) {
        logger.log(Level.INFO, "Socket created");
        return new MsgWebSocket(frontendService);
    }
}
