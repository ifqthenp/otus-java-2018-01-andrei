package com.otus.hw_16.front.websocket;

import com.otus.hw_16.front.frontendService.FrontendService;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class MsgWebSocketCreator implements WebSocketCreator {

    private FrontendService frontendService;

    public MsgWebSocketCreator(final FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Object createWebSocket(final ServletUpgradeRequest servletUpgradeRequest, final ServletUpgradeResponse servletUpgradeResponse) {
        return new MsgWebSocket(frontendService);
    }

}
