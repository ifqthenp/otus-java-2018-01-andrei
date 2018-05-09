package com.otus.hw_11.main;


import com.otus.hw_11.dbService.DBService;
import com.otus.hw_11.dbService.DBServiceImpl;
import com.otus.hw_11.entities.AddressDataSet;
import com.otus.hw_11.entities.PhoneDataSet;
import com.otus.hw_11.entities.UserDataSet;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        try {
            DBService dbService = new DBServiceImpl();
            String status = dbService.getLocalStatus();
            System.out.println("Status: " + status);

            AddressDataSet brickLane = new AddressDataSet("Brick Lane");
            AddressDataSet oldStreet = new AddressDataSet("Old Str");
            AddressDataSet liverpoolStreet = new AddressDataSet("Liverpool Str");
            AddressDataSet chelsea = new AddressDataSet("Chelsea");
            AddressDataSet bristol = new AddressDataSet("Bristol");

            UserDataSet carl = getCarl(chelsea);
            UserDataSet harry = getHarry(oldStreet);
            UserDataSet tony = getTony(brickLane);
            UserDataSet joe = getJoe(bristol);
            UserDataSet johnny = getJohnny(liverpoolStreet);

            dbService.save(carl);
            dbService.save(johnny);
            dbService.save(tony);
            dbService.save(joe);
            dbService.save(johnny);

            dbService.save(carl);
            dbService.save(harry);
            dbService.save(tony);
            dbService.save(joe);
            dbService.save(johnny);

            carl = dbService.read(1);
            System.out.println("Read by ID: " + carl);

            harry = dbService.read(2);
            System.out.println("Read by ID: " + harry);

            tony = dbService.read(3);
            System.out.println("Read by ID: " + tony);

            carl = dbService.readByName("Carl Cracker");
            System.out.println("Read by name: " + carl);

            harry = dbService.readByName("Harry Hacker");
            System.out.println("Read by name: " + harry);

            tony = dbService.readByName("tony");
            System.out.println("Read by name: " + tony);

            System.out.println("\nRead all from list: ");
            List<UserDataSet> dataSets = dbService.readAll();

            dataSets.forEach(u -> System.out.printf("%d %s %d %s %s %n", u.getId(), u.getName(), u.getAge(), u.getAddressDataSet(), u.getPhoneNumbers()));
            System.out.println();

            List<Long> ids = dbService.readAllIds();
            System.out.println(ids);

            dbService.shutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static UserDataSet getCarl(final AddressDataSet address)
    {
        UserDataSet carl = new UserDataSet();
        carl.setName("Carl Cracker");
        carl.setAge(18);
        carl.addAddress(address);
        carl.addPhoneNumber(new PhoneDataSet("01632 555888"));
        carl.addPhoneNumber(new PhoneDataSet("01632 000999"));
        carl.addPhoneNumber(new PhoneDataSet("01632 444777"));
        return carl;
    }

    private static UserDataSet getHarry(final AddressDataSet address)
    {
        UserDataSet harry = new UserDataSet();
        harry.setName("Harry Hacker");
        harry.setAge(24);
        harry.addAddress(address);
        harry.addPhoneNumber(new PhoneDataSet("01632 111222"));
        harry.addPhoneNumber(new PhoneDataSet("01632 333444"));
        return harry;
    }

    private static UserDataSet getTony(final AddressDataSet address)
    {
        UserDataSet tony = new UserDataSet();
        tony.setName("Tony Tester");
        tony.setAge(20);
        tony.addAddress(address);
        tony.addPhoneNumber(new PhoneDataSet("01632 777999"));
        return tony;
    }

    private static UserDataSet getJoe(final AddressDataSet address)
    {
        UserDataSet joe = new UserDataSet();
        joe.setName("Dodgy Joe");
        joe.setAge(31);
        joe.addAddress(address);
        joe.addPhoneNumber(new PhoneDataSet(null));
        return joe;
    }

    private static UserDataSet getJohnny(final AddressDataSet address)
    {
        UserDataSet johnny = new UserDataSet();
        johnny.setName("Johnny FooBar");
        johnny.setAge(25);
        johnny.addAddress(address);
        johnny.addPhoneNumber(new PhoneDataSet("01632 000111"));
        return johnny;
    }
}
