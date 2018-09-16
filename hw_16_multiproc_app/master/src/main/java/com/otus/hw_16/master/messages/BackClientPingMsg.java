package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

import java.time.LocalDateTime;

public class BackClientPingMsg extends Msg {

    private final LocalDateTime time;
    private final String message;

    public BackClientPingMsg() {
        super(BackClientPingMsg.class);
        this.time = LocalDateTime.now();
        this.message = null;
    }

    public BackClientPingMsg(String message) {
        super(BackClientPingMsg.class);
        this.time = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "BackClientPingMsg{" +
                "time=" + this.time +
                ", message='" + this.message + '\'' +
                '}';
    }

}
