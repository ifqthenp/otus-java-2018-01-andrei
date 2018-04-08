package com.otus.hw_08.testClasses;

public class BagOfArrays
{
    private Person[] people;
    private Object[] objects;

    public BagOfArrays()
    {
        people = new Person[]{ new Person(), new Person("John", 44), null };
        objects = new Object[]{ new Object(), new Person("John", 44), null };
    }

    public Person[] getPeople()
    {
        return people;
    }

    public void setPeople(final Person[] people)
    {
        this.people = people;
    }

    public Object[] getObjects()
    {
        return objects;
    }

    public void setObjects(final Object[] objects)
    {
        this.objects = objects;
    }

}
