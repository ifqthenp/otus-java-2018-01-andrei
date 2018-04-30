package com.otus.hw_11.dbService;

import com.otus.hw_11.entities.UserDataSet;

import java.util.List;

public interface DBService
{
    String getLocalStatus();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    void shutdown();
}
