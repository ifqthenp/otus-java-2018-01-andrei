package com.otus.hw_16.master.server;

import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.app.MsgWorker;
import com.otus.hw_16.master.channel.Blocks;
import com.otus.hw_16.master.channel.SocketMsgWorker;
import com.otus.hw_16.master.messages.BackClientPingMsg;
import com.otus.hw_16.master.messages.UserDataByIdRequest;
import com.otus.hw_16.master.messages.UserDataByIdResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoSocketMsgServer implements EchoSocketMsgServerMBean {

    private static final Logger logger = LoggerFactory.getLogger(EchoSocketMsgServer.class.getName());

    private static final int THREADS_NUMBER = 1;
    private static final int PORT = 5050;
    private static final int MIRROR_DELAY_MS = 100;

    private final ExecutorService executor;
    private final List<MsgWorker> workers;

    public EchoSocketMsgServer() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        workers = new CopyOnWriteArrayList<>();
    }

    @Blocks
    public void start() throws Exception {
        executor.submit(this::echo);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket socket = serverSocket.accept(); //blocks
                SocketMsgWorker client = new SocketMsgWorker(socket);
                client.init();
                workers.add(client);
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void echo() {
        while (true) {
            for (MsgWorker client : workers) {
                Msg msg = client.pool();
                while (msg != null) {
                    logger.info("Message received on server: {}", msg.toString());

                    for (MsgWorker worker : workers) {
                        sendMsg(msg, client, worker);
                    }

                    msg = client.pool();
                }
            }
            try {
                Thread.sleep(MIRROR_DELAY_MS);
            } catch (InterruptedException e) {
                logger.error(e.toString());
            }
        }
    }

    private void sendMsg(final Msg msg, final MsgWorker client, final MsgWorker worker) {
        if (msg instanceof BackClientPingMsg) {
            client.setIsFromBackend(true);
        } else if (msg instanceof UserDataByIdRequest) {
            if (worker.isFromBackend()) {
                worker.send(msg);
            }
        } else if (msg instanceof UserDataByIdResponse) {
            if (!worker.isFromBackend()) {
                worker.send(msg);
            }
        } else {
            logger.info("Unknown message: {}", msg.toString());
        }
    }

    @Override
    public boolean getRunning() {
        return true;
    }

    @Override
    public void setRunning(boolean running) {
        if (!running) {
            executor.shutdown();
            logger.info("Bye.");
        }
    }

}
