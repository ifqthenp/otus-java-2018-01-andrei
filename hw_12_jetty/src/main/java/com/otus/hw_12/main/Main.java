package com.otus.hw_12.main;

import com.otus.hw_12.listeners.AppInitializerListener;
import com.otus.hw_12.servlets.AdminServlet;
import com.otus.hw_12.servlets.LoginServlet;
import com.otus.hw_12.servlets.TemplateProcessor;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletContextListener;

public class Main
{
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "hw_12_jetty/public_html";

    public static void main(String[] args) throws Exception
    {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        TemplateProcessor templateProcessor = new TemplateProcessor();

        context.addServlet(new ServletHolder(new LoginServlet(templateProcessor, "anonymous")), "/login");
        context.addServlet(AdminServlet.class, "/admin");

        // Initialize DBService
        ServletContextListener scl = new AppInitializerListener();
        context.addEventListener(scl);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
