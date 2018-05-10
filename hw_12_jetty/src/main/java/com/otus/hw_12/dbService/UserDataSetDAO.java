package com.otus.hw_12.dbService;

import com.otus.hw_12.entities.UserDataSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDataSetDAO
{
    private Session session;

    public UserDataSetDAO(Session session)
    {
        this.session = session;
    }

    public long save(UserDataSet dataSet)
    {
        return (long) session.save(dataSet);
    }

    public void update(UserDataSet dataSet)
    {
        session.update(dataSet);
    }

    public UserDataSet read(long id)
    {
        return session.load(UserDataSet.class, id);
    }

    public UserDataSet readByName(String name)
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<UserDataSet> query = session.createQuery(criteria);
        return query.uniqueResult();
    }

    public List<UserDataSet> readAll()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list();
    }

    public List<Long> readAllIds()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.orderBy(builder.asc(from.get("id")));
        criteria.select(from.get("id"));
        return session.createQuery(criteria).getResultList();
    }
}
