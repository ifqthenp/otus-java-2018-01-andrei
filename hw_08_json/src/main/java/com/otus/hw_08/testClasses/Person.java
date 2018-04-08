package com.otus.hw_08.testClasses;

import java.util.Objects;

public class Person
{
    private String name;
    private int age;

    public Person(final String name, final int age)
    {
        this.name = name;
        this.age = age;
    }

    public Person() {}

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(final int age)
    {
        this.age = age;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, age);
    }

    @Override
    public String toString()
    {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
