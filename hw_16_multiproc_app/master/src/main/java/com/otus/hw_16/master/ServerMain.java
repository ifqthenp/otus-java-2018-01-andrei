package com.otus.hw_16.master;

import com.otus.hw_16.master.runner.ProcessRunnerImpl;
import com.otus.hw_16.master.server.EchoSocketMsgServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerMain {

    private static final Logger logger = LoggerFactory.getLogger(ServerMain.class.getName());

    private static final String BACKEND_START_COMMAND = "java -jar back/target/back.jar";
    private static final String FRONTEND_START_COMMAND = "cp front/target/front.war ${JETTY_HOME}/webapps/root.war";
    private static final int CLIENT_START_DELAY_SEC = 5;

    public static void main(String[] args) throws Exception {
        new ServerMain().start();
    }

    private void start() throws Exception {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        startClient(executorService, BACKEND_START_COMMAND);
//        startClient(executorService, FRONTEND_START_COMMAND);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Server");
        EchoSocketMsgServer server = new EchoSocketMsgServer();
        mbs.registerMBean(server, name);

        server.start();

        executorService.shutdown();
    }

    private void startClient(ScheduledExecutorService executorService, String command) {
        executorService.schedule(() -> {
            try {
                new ProcessRunnerImpl().start(command);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }, CLIENT_START_DELAY_SEC, TimeUnit.SECONDS);
    }

}
