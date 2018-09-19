package com.otus.hw_16.master.app;

public abstract class Msg {

    public static final String CLASS_NAME_VARIABLE = "className";
    public static final String USER_DATA_SET_BY_ID_REQUEST = "userDataByIdRequest";
    public static final String CACHE_UPDATE_REQUEST = "cacheUpdateRequest";

    private final String className;

    protected Msg(Class<?> klass) {
        this.className = klass.getName();
    }

}
