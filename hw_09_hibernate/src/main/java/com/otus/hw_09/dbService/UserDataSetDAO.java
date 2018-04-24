package com.otus.hw_09.dbService;

import com.otus.hw_09.dataset.UserDataSet;
import org.hibernate.Session;

public class UserDataSetDAO
{
    private Session session;

    public UserDataSetDAO(Session session)
    {
        this.session = session;
    }

    public void save(UserDataSet dataSet)
    {
        session.save(dataSet);
    }

    public UserDataSet read(final long id)
    {
        return session.load(UserDataSet.class, id);
    }
}
