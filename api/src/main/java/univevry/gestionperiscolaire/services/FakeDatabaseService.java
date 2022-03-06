package univevry.gestionperiscolaire.services;

import java.util.ArrayList;

import univevry.gestionperiscolaire.models.User;

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
