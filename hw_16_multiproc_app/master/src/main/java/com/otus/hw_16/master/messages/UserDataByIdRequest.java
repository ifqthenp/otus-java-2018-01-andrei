package com.otus.hw_16.master.messages;

import com.otus.hw_16.master.app.Msg;

public class UserDataByIdRequest extends Msg {

    private final String message;

    public UserDataByIdRequest(final String message) {
        super(UserDataByIdRequest.class);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UserDataByIdRequest{" +
                "message='" + message + '\'' +
                '}';
    }

}
