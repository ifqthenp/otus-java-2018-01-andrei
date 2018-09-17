package com.otus.hw_16.master.app;

import com.otus.hw_16.master.channel.Blocks;

import java.io.IOException;

public interface MsgWorker {

    void send(Msg msg);

    Msg pool();

    @Blocks
    Msg take() throws InterruptedException;

    void close() throws IOException;

    void setIsFromBackend(boolean isFromBackend);

    boolean isFromBackend();

}
