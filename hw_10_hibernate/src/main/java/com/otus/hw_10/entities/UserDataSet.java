package com.otus.hw_10.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER_DATASETS")
public class UserDataSet extends DataSet
{
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE")
    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDataSet", fetch = FetchType.LAZY)
    private List<PhoneDataSet> phoneNumbers = new ArrayList<>();

    /* Helper method for bidirectional One-To-One association */
    public void addAddress(AddressDataSet address)
    {
        this.address = address;
        address.setUserDataSet(this);
    }

    /* Helper method for bidirectional One-To-Many association */
    public void addPhoneNumber(PhoneDataSet phoneDataSet)
    {
        this.phoneNumbers.add(phoneDataSet);
        phoneDataSet.setUserDataSet(this);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
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

    public AddressDataSet getAddressDataSet()
    {
        return address;
    }

    public void setAddressDataSet(final AddressDataSet addressDataSet)
    {
        this.address = addressDataSet;
    }

    public List<PhoneDataSet> getPhoneNumbers()
    {
        return phoneNumbers;
    }

    public void setPhoneNumbers(final List<PhoneDataSet> phoneNumbers)
    {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserDataSet that = (UserDataSet) o;
        return getId() == that.getId() && age == that.age &&
            Objects.equals(name, that.name) &&
            Objects.equals(address, that.address) &&
            Objects.equals(phoneNumbers, that.phoneNumbers);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), name, age, address, phoneNumbers);
    }

    @Override
    public String toString()
    {
        return "UserDataSet{" +
            "id='" + getId() + '\'' +
            ", name='" + name + '\'' +
            ", age='" + age + '\'' +
            ", address=" + address +
            ", phoneNumbers=" + phoneNumbers +
            '}';
    }
}

