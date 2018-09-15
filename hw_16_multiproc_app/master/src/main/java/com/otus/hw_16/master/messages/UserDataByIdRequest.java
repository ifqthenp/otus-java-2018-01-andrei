package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

import java.time.LocalDateTime;

public class UserDataByIdRequest extends Msg {

    private final String message;
    private final LocalDateTime localDateTime;

    public UserDataByIdRequest() {
        super(UserDataByIdRequest.class);
        message = null;
        localDateTime = LocalDateTime.now();
    }

    public UserDataByIdRequest(final String message) {
        super(UserDataByIdRequest.class);
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "UserDataByIdRequest{" +
                "message='" + message + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

}
