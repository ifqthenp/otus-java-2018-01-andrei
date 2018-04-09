package com.otus.hw_09.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionHelper
{
    private ConnectionHelper() {}

    public static Connection getConnection()
    {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String propsFileName = "db.properties";

        try {
            Properties props = new Properties();
            try (InputStream in = classLoader.getResourceAsStream(propsFileName)) {
                props.load(in);
            }

            final String url = props.getProperty("db.url");

            String username = props.getProperty("db.user");
            if (username == null) username = "";

            String password = props.getProperty("db.pass");
            if (password == null) password = "";

            return DriverManager.getConnection(url, username, password);
        }
        catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
