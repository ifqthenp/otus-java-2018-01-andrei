package com.otus.hw_11.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ADDRESSES")
public class AddressDataSet extends DataSet
{
    @Column(name = "STREET")
    private String street;

    @OneToOne(mappedBy = "address")
    private UserDataSet userDataSet;

    public AddressDataSet() {}

    public AddressDataSet(final String street)
    {
        this.street = street;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(final String street)
    {
        this.street = street;
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
        final AddressDataSet that = (AddressDataSet) o;
        return getId() == that.getId() && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), street);
    }

    @Override
    public String toString()
    {
        return "AddressDataSet{" +
            "id=" + getId() +
            ", street='" + street + '\'' +
            '}';
    }
}
