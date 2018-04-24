package com.otus.hw_09.main;

import com.otus.hw_09.dataset.UserDataSet;
import com.otus.hw_09.dbService.DBService;
import com.otus.hw_09.dbService.DBServiceHibernateImp;

/**
 * {@code Main} class.
 */
public class Main
{
    public static void main(String[] args)
    {
        DBService dbService = new DBServiceHibernateImp();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        dbService.save(new UserDataSet("Carl Cracker", 18));
        dbService.save(new UserDataSet("Tony Tester", 24));
        dbService.save(new UserDataSet("Harry Hacker", 22));

        UserDataSet carl = dbService.load(1);
        UserDataSet tony = dbService.load(2);
        UserDataSet harry = dbService.load(3);

        System.out.println(carl.getId() + " " + carl.getName() + " " + carl.getAge());
        System.out.println(tony.getId() + " " + tony.getName() + " " + tony.getAge());
        System.out.println(harry.getId() + " " + harry.getName() + " " + harry.getAge());

        dbService.shutDown();
    }
}
