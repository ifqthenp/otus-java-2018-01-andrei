package com.otus.hw_16.front.frontendService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.otus.hw_16.front.websocket.MsgWebSocketHandler;
import com.otus.hw_16.master.app.Msg;
import com.otus.hw_16.master.app.MsgWorker;
import com.otus.hw_16.master.messages.CacheUpdateRequest;
import com.otus.hw_16.master.messages.CacheUpdateResponse;
import com.otus.hw_16.master.messages.UserDataByIdRequest;
import com.otus.hw_16.master.messages.UserDataByIdResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FrontendServiceImpl implements FrontendService {

    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class.getName());

    private Gson gson;
    private MsgWorker frontClient;
    private MsgWebSocketHandler msgWebSocketHandler;

    public FrontendServiceImpl(final MsgWebSocketHandler msgWebSocketHandler, final MsgWorker frontClient) {
        this.gson = new Gson();
        this.frontClient = frontClient;
        this.msgWebSocketHandler = msgWebSocketHandler;
    }

    @Override
    public void init() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Msg msg = frontClient.take();
                    logger.info("Message received: {}", msg.toString());

                    if (msg instanceof UserDataByIdResponse) {
                        UserDataByIdResponse response = (UserDataByIdResponse) msg;
                        handleResponseToWebSocket(response.getMessage());
                    } else if (msg instanceof CacheUpdateResponse) {
                        CacheUpdateResponse response = (CacheUpdateResponse) msg;
                        handleResponseToWebSocket(response.getMessage());
                    }

                }
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        });
    }

    @Override
    public void handleRequestFromWebSocket(final String message) {
        JsonObject jObject = gson.fromJson(message, JsonObject.class);
        String className = jObject.get("className").getAsString();
        Msg msg = null;

        switch (className) {
            case Msg.USER_DATA_SET_BY_ID_REQUEST:
                String messageValue = jObject.get("message").getAsString();
                msg = new UserDataByIdRequest(messageValue);
                frontClient.send(msg);
                break;
            case Msg.CACHE_UPDATE_REQUEST:
                msg = new CacheUpdateRequest();
                frontClient.send(msg);
                break;
            default:
                throw new IllegalStateException("Unknown type of message: " + message);
        }
    }

    @Override
    public void handleResponseToWebSocket(final String message) {
        msgWebSocketHandler.sendToWebSockets(message);
    }

    @Override
    public MsgWebSocketHandler getMsgWebSocketHandler() {
        return Objects.requireNonNull(this.msgWebSocketHandler);
    }

}
