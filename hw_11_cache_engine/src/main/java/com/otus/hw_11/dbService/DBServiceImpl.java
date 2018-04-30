package com.otus.hw_11.dbService;

import com.otus.hw_11.cache.CacheEngine;
import com.otus.hw_11.cache.CacheEngineImp;
import com.otus.hw_11.cache.MyElement;
import com.otus.hw_11.entities.UserDataSet;
import com.otus.hw_11.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBServiceImpl implements DBService
{
    private final static int MYSQL_FIRST_GENERATED_ID = 1;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final CacheEngine<Long, UserDataSet> cache = new CacheEngineImp<>(10, 0, 0, true);
    private long lastInsertId;

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
            lastInsertId = dataSet.getId();
            putInCache(new MyElement(lastInsertId, dataSet));
        } else {
            putInCache(new MyElement(dataSetKey, dataSet));
        }
    }

    public UserDataSet read(long id)
    {
        MyElement<Long, UserDataSet> cachedElement = cache.get(id);
        if (cachedElement == null) {
            UserDataSet user = runInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                return dao.read(id);
            });
            putInCache(new MyElement<>(user.getId(), user));
        }
        return cachedElement.getValue();
    }

    public UserDataSet readByName(String name)
    {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            UserDataSet userDataSet = dao.readByName(name);
            if (userDataSet != null) {
                putInCache(new MyElement(userDataSet.getId(), userDataSet));
                return userDataSet;
            }
            return null;
        });
    }

    public List<UserDataSet> readAll()
    {
        List<UserDataSet> result = new ArrayList<>((int) lastInsertId);
        for (int i = MYSQL_FIRST_GENERATED_ID; i <= lastInsertId; i++) {
            result.add(this.read(i));
        }
        return result;
    }

    public void shutdown()
    {
        sessionFactory.close();
        cache.dispose();
    }

    private void putInCache(MyElement<Long, UserDataSet> element)
    {
        cache.put(element);
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
