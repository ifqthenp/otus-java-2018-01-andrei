package com.otus.hw_08.testClasses;

import java.util.Objects;

public class Employee extends Person
{
    private int id;

    public Employee(final String name, final int age, final int id)
    {
        super(name, age);
        this.id = id;
    }

    public Employee(final int id)
    {
        this.id = id;
    }

    public Employee()
    {
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id);
    }
}
