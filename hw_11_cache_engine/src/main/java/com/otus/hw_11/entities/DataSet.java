package com.otus.hw_11.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class DataSet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;

    public DataSet() {}

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }
}
