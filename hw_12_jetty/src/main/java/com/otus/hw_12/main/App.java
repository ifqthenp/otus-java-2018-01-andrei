package com.otus.hw_12.main;

import com.otus.hw_12.dbService.DBService;
import com.otus.hw_12.dbService.DBServiceImpl;
import com.otus.hw_12.entities.AddressDataSet;
import com.otus.hw_12.entities.PhoneDataSet;
import com.otus.hw_12.entities.UserDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App
{
    public static void main(String[] args)
    {
        try {
            DBService dbService = new DBServiceImpl();
            String status = dbService.getLocalStatus();
            System.out.println("Status: " + status);

            List<AddressDataSet> addresses = getAddressList();

            for (int i = 0; i < 15; i++) {
                dbService.save(getUser("user" + i, addresses.get(new Random().nextInt(5))));
            }

            UserDataSet user5 = dbService.read(5);
            user5.addAddress(new AddressDataSet("Scotland"));
            user5.setName("Andrew");

            dbService.save(user5);

            List<UserDataSet> dataSets = dbService.readAll();
            for (UserDataSet el : dataSets) {
                System.out.println(el);
            }

            UserDataSet aUser = dbService.readByName("Andrew");
            System.out.println(aUser);

            List<Long> userIds = dbService.readAllIds();
            System.out.println(userIds);

            dbService.shutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserDataSet getUser(final String name, final AddressDataSet address)
    {
        UserDataSet user = new UserDataSet();
        user.setName(name);
        user.setAge(new Random().nextInt(50) + 15);
        user.addAddress(address);
        user.addPhoneNumber(new PhoneDataSet("00000 555888"));
        user.addPhoneNumber(new PhoneDataSet("22222 444777"));
        return user;
    }

    public static List<AddressDataSet> getAddressList()
    {
        List<AddressDataSet> addresses = new ArrayList<>();
        addresses.add(new AddressDataSet("Brick Lane"));
        addresses.add(new AddressDataSet("Old Str"));
        addresses.add(new AddressDataSet("Liverpool Str"));
        addresses.add(new AddressDataSet("Chelsea"));
        addresses.add(new AddressDataSet("Bristol"));
        return addresses;
    }
}
