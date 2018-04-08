package com.otus.hw_08.testClasses;

public class BagOfObjects
{
    private Person person;
    private Object object;

    public BagOfObjects(final Person person, final Object object)
    {
        this.person = person;
        this.object = object;
    }

    public BagOfObjects()
    {
    }

    @Override
    public String toString()
    {
        return "BagOfObjects{" +
            "person=" + person +
            ", object=" + object +
            '}';
    }
}
