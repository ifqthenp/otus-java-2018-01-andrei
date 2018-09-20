package com.otus.hw_16.front.listeners;

import com.otus.hw_16.front.frontendService.FrontendService;
import com.otus.hw_16.master.app.MsgWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        context.setAttribute("applicationContext", ac);

        FrontendService front = ac.getBean(FrontendService.class);
        context.setAttribute("frontendService", front);

        MsgWorker msgWorker = ac.getBean(MsgWorker.class);
        context.setAttribute("msgWorker", msgWorker);

    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {

    }

}
