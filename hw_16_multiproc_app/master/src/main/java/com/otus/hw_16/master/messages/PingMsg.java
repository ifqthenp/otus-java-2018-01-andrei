package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

public class PingMsg extends Msg {

    private final long time;

    public PingMsg() {
        super(PingMsg.class);
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "PingMsg{" + "time=" + time + '}';
    }

}
