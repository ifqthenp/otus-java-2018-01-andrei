package com.otus.hw_11.dbService;

import com.otus.hw_11.cache.CacheEngine;
import com.otus.hw_11.cache.CacheEngineImp;
import com.otus.hw_11.cache.MyElement;
import com.otus.hw_11.entities.UserDataSet;
import com.otus.hw_11.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBServiceImpl implements DBService
{
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final CacheEngine<Long, UserDataSet> cache = new CacheEngineImp<>(10, 0, 0, true);

    public String getLocalStatus()
    {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    public void save(UserDataSet dataSet)
    {
        long dataSetKey = dataSet.getId();
        if (dataSetKey == -1) {
            saveInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                dao.save(dataSet);
            });
            cache.put(new MyElement(dataSet.getId(), dataSet));
        } else {
            cache.put(new MyElement(dataSetKey, dataSet));
        }
    }

    public UserDataSet read(long id)
    {
        if (cache.get(id) == null) {
            UserDataSet user = runInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                return dao.read(id);
            });
            cache.put(new MyElement<>(user.getId(), user));
        }
        return cache.get(id).getValue();
    }

    public UserDataSet readByName(String name)
    {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            UserDataSet userDataSet = dao.readByName(name);
            if (userDataSet != null) {
                cache.put(new MyElement(userDataSet.getId(), userDataSet));
                return userDataSet;
            }
            return null;
        });
    }

    public List<UserDataSet> readAll()
    {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            List<UserDataSet> list = dao.readAll();
            list.forEach(user -> cache.put(new MyElement<>(user.getId(), user)));
            return list;
        });
    }

    public void shutdown()
    {
        sessionFactory.close();
        cache.dispose();
    }

    private <R> R runInSession(Function<Session, R> function)
    {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    private <T> void saveInSession(Consumer<Session> consumer)
    {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }
}
