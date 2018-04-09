package com.otus.hw_09.dataset;

public class UserDataSet extends DataSet {

    private String name;
    private int age;

    public UserDataSet(final String name, final int age)
    {
        this.name = name;
        this.age = age;
    }

    public UserDataSet(final long id, final String name, final int age)
    {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return "UserDataSet{" +
            "id=" + this.getId() +
            ", name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
