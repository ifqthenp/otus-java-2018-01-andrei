package com.otus.hw_13.dbService;

import com.otus.hw_13.cacheService.CacheService;
import com.otus.hw_13.entities.dataset.UserDataSet;
import com.otus.hw_13.util.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.cache.Cache;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBServiceImpl implements DBService
{
    private final static int INITIAL_ID = -1;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Cache<Long, UserDataSet> cache;

    public DBServiceImpl(final CacheService cacheService)
    {
        this.cache = Objects.requireNonNull(cacheService.getUserDataSetCache());
    }

    public String getLocalStatus()
    {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    public void save(UserDataSet dataSet)
    {
        long existingId = dataSet.getId();
        if (existingId == INITIAL_ID) {
            long generatedId = runInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                return dao.save(dataSet);
            });
            putInCache(generatedId, dataSet);
        } else {
            updateInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                dao.update(dataSet);
            });
            putInCache(existingId, dataSet);
        }
    }

    public UserDataSet read(long id)
    {
        UserDataSet user = cache.get(id);
        if (user == null) {
            user = runInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                return dao.read(id);
            });
            putInCache(user.getId(), user);
        }
        return user;
    }

    public UserDataSet readByName(String name)
    {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            UserDataSet userDataSet = dao.readByName(name);
            if (userDataSet != null) {
                putInCache(userDataSet.getId(), userDataSet);
            }
            return userDataSet;
        });
    }

    public List<UserDataSet> readAll()
    {
        List<UserDataSet> users = runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.readAll();
        });
        users.forEach(user -> putInCache(user.getId(), user));
        return users;
    }

    public List<Long> readAllIds()
    {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.readAllIds();
        });
    }

    public void shutdown()
    {
        sessionFactory.close();
    }

    private void putInCache(final Long key, final UserDataSet value)
    {
        cache.put(key, value);
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

    private <T> void updateInSession(Consumer<Session> consumer)
    {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }
}
