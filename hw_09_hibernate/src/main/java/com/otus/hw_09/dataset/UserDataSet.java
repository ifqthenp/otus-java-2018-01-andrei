package com.otus.hw_09.dataset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USER_DATASET")
public class UserDataSet extends DataSet
{
    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    public UserDataSet() {}

    public UserDataSet(final String name, final int age)
    {
        this.name = name;
        this.age = age;
    }

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
        final UserDataSet that = (UserDataSet) o;
        return age == that.age &&
            getId() == that.getId() &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), name, age);
    }

    @Override
    public String toString()
    {
        return "UserDataSet{" +
            "id=" + getId() +
            ", name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
