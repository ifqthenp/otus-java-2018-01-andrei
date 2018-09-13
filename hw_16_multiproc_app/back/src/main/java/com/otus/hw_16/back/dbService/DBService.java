package com.otus.hw_16.back.dbService;

import com.otus.hw_16.back.entities.UserDataSet;

import java.util.List;

public interface DBService {

    void init();

    String getLocalStatus();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    List<Long> readAllIds();

    void shutdown();

}
