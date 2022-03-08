package api.services;

import java.util.ArrayList;
import java.util.List;

import api.models.User;

public class FakeDatabaseService {

  private static List<User> users = new ArrayList<User>();

  public static void initializeFakeUsers() {
    var mathieu = new User("Mathieu", "BES", "pwdTest");
    var garik = new User("Garik", "DERMINJYAN", "flk");
    var florian = new User("Florian", "CARBONI", "dksjskj");

    users.add(mathieu);
    users.add(garik);
    users.add(florian);
  }

  public static List<User> getUsers() {
    return users;
  }

}