package fr.univevry.api.services;

import fr.univevry.api.models.User;

import java.util.ArrayList;

public class FakeDatabaseService {

    private static ArrayList<User> users = new ArrayList<User>();

    public static void initializeFakeUsers() {
        var mathieu = new User("Mathieu", "BES");
        var garik = new User("Garik", "DERMINJYAN");
        var florian = new User("Florian", "QUINESTPASLA");

        users.add(mathieu);
        users.add(garik);
        users.add(florian);
    }

    public static User[] getUsers() {
        return users.toArray(new User[0]);
    }

}
