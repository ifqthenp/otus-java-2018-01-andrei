package com.otus.hw_15.services.dbService;

import com.otus.hw_15.entities.dataset.UserDataSet;
import com.otus.hw_15.messageSystem.Address;
import com.otus.hw_15.messageSystem.Addressee;
import com.otus.hw_15.messageSystem.MessageSystem;

import java.util.List;

public interface DBService extends Addressee
{
    void init();

    String getLocalStatus();

    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    UserDataSet readByName(String name);

    List<UserDataSet> readAll();

    List<Long> readAllIds();

    void shutdown();

    @Override
    Address getAddress();

    @Override
    MessageSystem getMessageSystemFromContext();
}
