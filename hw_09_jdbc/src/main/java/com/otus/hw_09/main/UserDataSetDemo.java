package com.otus.hw_09.main;

import com.otus.hw_09.dataset.DataSet;
import com.otus.hw_09.dataset.UserDataSet;
import com.otus.hw_09.dbService.DBService;
import com.otus.hw_09.dbService.DBServiceUpdate;

import java.util.ArrayList;
import java.util.List;

public class UserDataSetDemo
{
    public static void main(String[] args)
    {
        dropTable();
        createTable();

        DBService dbService = new DBServiceUpdate();

        List<UserDataSet> users = new ArrayList<>();
        users.add(new UserDataSet("Harry Hacker", 21));
        users.add(new UserDataSet("Carl Cracker", 18));
        users.add(new UserDataSet("Tony Tester", 25));

        System.out.println("\nUserDataSet objects to be saved to DB: ");
        users.forEach(System.out::println);
        System.out.println();

        System.out.print("Saving UserDataSet objects to DB... ");
        dbService.saveUsersToDb(users);
        System.out.println("Done.\n");

        System.out.println("UserDataSet objects with DB generated IDs: ");
        users.forEach(System.out::println);
        System.out.println();

        List<Integer> idList = new ArrayList<>();
        idList.add(9);
        idList.add(0);
        idList.add(3);
        idList.add(4);
        idList.add(1);
        idList.add(2);

        List<? extends DataSet> loadedUsers = dbService.loadUsersFromDb(idList, UserDataSet.class);

        System.out.println("UserDataSet objects loaded from database: ");
        loadedUsers.forEach(System.out::println);
    }

    private static void createTable()
    {
        try (DBServiceUpdate dbService = new DBServiceUpdate()) {
            System.out.println(dbService.getMetaData());
            dbService.createTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dropTable()
    {
        try (DBServiceUpdate dbService = new DBServiceUpdate()) {
            dbService.deleteTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
