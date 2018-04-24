package com.otus.hw_09.dbService;

import com.otus.hw_09.dataset.UserDataSet;

public interface DBService
{
    String getLocalStatus();

    void save(final UserDataSet user);

    UserDataSet load(long id);

    void shutDown();
}
