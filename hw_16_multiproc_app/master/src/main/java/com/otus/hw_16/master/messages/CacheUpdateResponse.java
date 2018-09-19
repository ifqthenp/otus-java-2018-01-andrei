package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

public class CacheUpdateResponse extends Msg {

    private final String message;

    public CacheUpdateResponse(final String message) {
        super(CacheUpdateResponse.class);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CacheUpdateResponse{" +
                "message='" + message + '\'' +
                '}';
    }

}
