package com.otus.hw_09.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface TResultHandler<T>
{
    T handle(ResultSet resultSet) throws SQLException;
}
