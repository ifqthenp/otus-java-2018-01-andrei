package com.otus.hw_15.entities.dataset;

import javax.persistence.*;

@MappedSuperclass
public abstract class DataSet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;

    public DataSet()
    {
        this.setId(-1);
    }

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }
}
