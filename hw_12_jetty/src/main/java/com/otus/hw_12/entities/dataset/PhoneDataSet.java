package com.otus.hw_12.entities.dataset;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PHONES")
public class PhoneDataSet extends DataSet
{
    @Column(name = "NUMBER")
    private String number;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDataSet userDataSet;

    public PhoneDataSet() {}

    public PhoneDataSet(final String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(final String number)
    {
        this.number = number;
    }

    public UserDataSet getUserDataSet()
    {
        return userDataSet;
    }

    public void setUserDataSet(final UserDataSet userDataSet)
    {
        this.userDataSet = userDataSet;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PhoneDataSet that = (PhoneDataSet) o;
        return getId() == that.getId() && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), number);
    }

    @Override
    public String toString()
    {
        return "PhoneDataSet{" +
                "id=" + getId() +
                ", number='" + number + '\'' +
                '}';
    }
}
