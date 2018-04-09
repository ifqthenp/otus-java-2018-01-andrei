package com.otus.hw_09.dataset;

public abstract class DataSet
{
    private long id;

    public DataSet()
    {
        this.id = -1;
    }

    public DataSet(final long id)
    {
        this.id = id;
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
