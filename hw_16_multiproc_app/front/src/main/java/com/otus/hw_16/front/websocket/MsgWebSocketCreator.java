package com.otus.hw_16.front.websocket;

import com.otus.hw_16.front.frontendService.FrontendService;
import com.otus.hw_16.master.app.MsgWorker;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgWebSocketCreator implements WebSocketCreator {

    private final static Logger logger = LoggerFactory.getLogger(MsgWebSocketCreator.class.getName());

    private MsgWorker frontClient;
    private FrontendService frontendService;

    public MsgWebSocketCreator(final FrontendService frontendService, final MsgWorker frontClient) {
        this.frontClient = frontClient;
        this.frontendService = frontendService;
    }

    @Override
    public Object createWebSocket(final ServletUpgradeRequest servletUpgradeRequest, final ServletUpgradeResponse servletUpgradeResponse) {
        logger.info("Websocket created");
        return new MsgWebSocket(frontendService, frontClient);
    }

}
