package api.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.User;

@Service
@Scope("singleton")
public class FakeDatabaseService {

  private List<User> users = new ArrayList<User>();

  @PostConstruct
  public void initializeFakeUsers() {
    var mathieu = new User("Mathieu", "BES", "pwdTest");
    var garik = new User("Garik", "DERMINJYAN", "flk");
    var florian = new User("Florian", "CARBONI", "dksjskj");

    users.add(mathieu);
    users.add(garik);
    users.add(florian);
  }

  public List<User> getUsers() {
    return users;
  }

}