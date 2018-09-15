package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

import java.time.LocalDateTime;

public class UserDataByIdResponse extends Msg {

    private final String message;
    private final LocalDateTime localDateTime;

    public UserDataByIdResponse() {
        super(UserDataByIdResponse.class);
        message = null;
        localDateTime = LocalDateTime.now();
    }

    public UserDataByIdResponse(final String message) {
        super(UserDataByIdResponse.class);
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
        return "UserDataByIdResponse{" +
                "message='" + message + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

}
