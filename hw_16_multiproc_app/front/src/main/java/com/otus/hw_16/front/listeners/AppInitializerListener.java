package com.otus.hw_16.front.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        sce.getServletContext().setAttribute("applicationContext", ac);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {

    }

}
