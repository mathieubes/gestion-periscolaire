package api.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.User;

@Service
@Scope("singleton")
public class FakeDatabaseService {

  private ArrayList<User> users = new ArrayList<User>();

  @PostConstruct
  public void initializeFakeUsers() {
    var mathieu = new User("Mathieu", "BES", "pwdTest1", "matmat@gmail.fr", "surLaA6", "06qlqchose");
    var garik = new User("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose");
    var florian = new User("Florian", "CARBONI", "pwdTest3", "bgDeL_IBGBI@gmail.fr", "versRis", "118 218");
    var fawaz = new User("Fawaz", "Moussougan", "pwdTest4", "whiteHatsMaster@gmail.fr", "unknown", "Call the police");

    users.add(mathieu);
    users.add(garik);
    users.add(florian);
    users.add(fawaz);
  }

  public ArrayList<User> getUsers() {
    return users;
  }

}