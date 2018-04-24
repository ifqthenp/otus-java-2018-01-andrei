package com.otus.hw_09.dataset;

import javax.persistence.*;

@MappedSuperclass
public abstract class DataSet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
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
