package com.otus.hw_16.back.util;

import com.otus.hw_16.back.dbService.DBService;
import com.otus.hw_16.back.entities.AddressDataSet;
import com.otus.hw_16.back.entities.PhoneDataSet;
import com.otus.hw_16.back.entities.UserDataSet;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class DatabaseUtil {

    public static void emulateLoad(ApplicationContext context) {
        DBService dbService = context.getBean(DBService.class);

        List<AddressDataSet> addresses = getAddressList();
        for (int i = 0; i < 11; i++) {
            dbService.save(getUser("user" + i, addresses.get(new Random().nextInt(5))));
        }

        dbService.shutdown();
    }

    public static UserDataSet getUser(final String name, final AddressDataSet address) {
        UserDataSet user = new UserDataSet();
        user.setName(name);
        user.setAge(new Random().nextInt(50) + 15);
        user.addAddress(address);
        user.addPhoneNumber(new PhoneDataSet("00000 555888"));
        user.addPhoneNumber(new PhoneDataSet("22222 444777"));
        return user;
    }

    public static List<AddressDataSet> getAddressList() {
        List<AddressDataSet> addresses = new ArrayList<>();
        addresses.add(new AddressDataSet("Brick Lane"));
        addresses.add(new AddressDataSet("Old Str"));
        addresses.add(new AddressDataSet("Liverpool Str"));
        addresses.add(new AddressDataSet("Chelsea"));
        addresses.add(new AddressDataSet("Bristol"));
        return addresses;
    }

}
