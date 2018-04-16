package com.otus.hw_09.dbService;

import com.otus.hw_09.dataset.DataSet;

import java.util.List;

public interface DBService extends AutoCloseable
{
    String getMetaData();

    void createTable();

    void deleteTable();

    <T extends DataSet> void saveUsersToDb(final List<T> users);

    <T extends DataSet> List<T> loadUsersFromDb(List<Integer> userIds, Class<T> cl);
}
