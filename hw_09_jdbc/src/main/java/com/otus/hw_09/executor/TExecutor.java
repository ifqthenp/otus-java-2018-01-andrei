package com.otus.hw_09.executor;

import com.otus.hw_09.dataset.DataSet;
import com.otus.hw_09.dbService.DBServiceUpdate;
import com.otus.hw_09.utils.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class TExecutor extends DBServiceUpdate
{
    public <T extends DataSet> void save(final T user)
    {
        final Map<String, Object> fields = ReflectionHelper.getFieldNames(user);
        final String className = user.getClass().getSimpleName();

        String name = (String) fields.getOrDefault("name", null);
        int age = (int) fields.getOrDefault("age", null);

        final String insert = "INSERT INTO " + className + "(`name`, `age`) VALUES (?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) user.setId(keys.getLong(1));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DataSet> T load(final long id, final Class<T> cl)
    {
        final String query = "SELECT * FROM " + cl.getSimpleName() + " WHERE id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                final String name = rs.getString(2);
                final int age = rs.getInt(3);

                return cl.getConstructor(long.class, String.class, int.class)
                    .newInstance(id, name, age);
            }
        }
        catch (SQLException | InstantiationException
            | InvocationTargetException | NoSuchMethodException
            | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
