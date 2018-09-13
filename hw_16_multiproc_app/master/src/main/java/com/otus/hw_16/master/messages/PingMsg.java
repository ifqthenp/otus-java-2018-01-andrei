package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

import java.time.LocalDateTime;

public class PingMsg extends Msg {

    private final LocalDateTime time;
    private final String message;

    public PingMsg() {
        super(PingMsg.class);
        this.time = LocalDateTime.now();
        this.message = null;
    }

    public PingMsg(String message) {
        super(PingMsg.class);
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
        return "PingMsg{" +
                "time=" + this.time +
                '}';
    }

}
