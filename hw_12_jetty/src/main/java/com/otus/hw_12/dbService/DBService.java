package com.otus.hw_12.dbService;

import com.otus.hw_12.entities.dataset.UserDataSet;

import java.util.List;

public interface DBService
{
    String getLocalStatus();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    List<Long> readAllIds();

    void shutdown();
}
