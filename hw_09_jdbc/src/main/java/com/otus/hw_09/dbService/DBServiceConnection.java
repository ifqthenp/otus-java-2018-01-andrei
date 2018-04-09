package com.otus.hw_09.dbService;

import com.otus.hw_09.utils.ConnectionHelper;
import com.otus.hw_09.dataset.DataSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBServiceConnection implements DBService
{
    private final Connection connection;

    DBServiceConnection()
    {
        connection = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData()
    {
        try {
            return "Connected to: " + getConnection().getMetaData().getURL() + "\n" +
                "DB name: " + getConnection().getMetaData().getDatabaseProductName() + "\n" +
                "DB version: " + getConnection().getMetaData().getDatabaseProductVersion() + "\n" +
                "Driver: " + getConnection().getMetaData().getDriverName();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void createTable() {}

    @Override
    public void deleteTable() {}

    @Override
    public <T extends DataSet> void saveUsersToDb(final List<T> users) {}

    @Override
    public <T extends DataSet> List<T> loadUsersFromDb(final List<Integer> idList, final Class<T> cl)
    {
        return null;
    }

    @Override
    public void close() throws Exception
    {
        connection.close();
    }

    protected Connection getConnection()
    {
        return connection;
    }
}
