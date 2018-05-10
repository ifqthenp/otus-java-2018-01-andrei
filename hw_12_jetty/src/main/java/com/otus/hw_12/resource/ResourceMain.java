package com.otus.hw_12.resource;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class ResourceMain {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "hw_12_jetty/public_html";

    public static void main(String[] args) throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        Server server = new Server(PORT);
        server.setHandler(resourceHandler);

        server.start();
        server.join();
    }
}
