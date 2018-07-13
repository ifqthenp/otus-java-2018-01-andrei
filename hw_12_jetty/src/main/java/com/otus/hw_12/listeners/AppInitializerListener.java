package com.otus.hw_12.listeners;

import com.otus.hw_12.dbService.DBService;
import com.otus.hw_12.dbService.DBServiceImpl;
import com.otus.hw_12.entities.dataset.AddressDataSet;
import com.otus.hw_12.entities.dataset.PhoneDataSet;
import com.otus.hw_12.entities.dataset.UserDataSet;
import com.otus.hw_12.util.ehcache.EhCacheUtil;

import javax.cache.Cache;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebListener
public class AppInitializerListener implements ServletContextListener
{
    private DBService dbService;

    @Override
    public void contextInitialized(final ServletContextEvent sce)
    {
        try {
            Cache<Long, UserDataSet> cache = new EhCacheUtil().getUserDataSetCache();

            dbService = new DBServiceImpl(cache);

            String status = dbService.getLocalStatus();
            System.out.println("Status: " + status);

            List<AddressDataSet> addresses = getAddressList();

            for (int i = 0; i < 11; i++) {
                dbService.save(getUser("user" + i, addresses.get(new Random().nextInt(5))));
            }

            UserDataSet user5 = dbService.read(5);
            user5.addAddress(new AddressDataSet("Scotland"));
            user5.setName("Andrew");

            dbService.save(user5);

            UserDataSet aUser = dbService.readByName("Andrew");

            user5 = dbService.read(5);
            UserDataSet user6 = dbService.read(6);
            UserDataSet user7 = dbService.read(7);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce)
    {
        dbService.shutdown();
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
