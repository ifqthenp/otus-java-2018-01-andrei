package com.otus.hw_09.executor;

import com.otus.hw_09.dataset.DataSet;
import com.otus.hw_09.dbService.DBServiceUpdate;
import com.otus.hw_09.utils.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TExecutor extends DBServiceUpdate
{
    public <T extends DataSet> void save(final T user)
    {
        Field[] fields = ReflectionHelper.getObjectFields(user);
        final String insert = ReflectionHelper.getInsertString(user);

        try (PreparedStatement stmt = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, (String) fields[0].get(user));
            stmt.setInt(2, (Integer) fields[1].get(user));
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) user.setId(keys.getLong(1));
        }
        catch (SQLException | IllegalAccessException e) {
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
