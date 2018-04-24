package com.otus.hw_09.dbService;

import com.otus.hw_09.dataset.UserDataSet;
import com.otus.hw_09.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

public class DBServiceHibernateImp implements DBService
{
    private final SessionFactory sessionFactory;

    public DBServiceHibernateImp()
    {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String getLocalStatus()
    {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    @Override
    public void save(final UserDataSet user)
    {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(user);
        }
    }

    @Override
    public UserDataSet load(final long id)
    {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.read(id);
        });
    }

    @Override
    public void shutDown()
    {
        sessionFactory.close();
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
}
