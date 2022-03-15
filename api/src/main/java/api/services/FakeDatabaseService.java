package api.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.Parent;
import api.models.User;

@Service
@Scope("singleton")
public class FakeDatabaseService {
  private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Parent> parents = new ArrayList<Parent>();

  private User mathieu;
  private User garik;
  private User florian;
  private User fawaz;

  private Parent parentMathieu;

  @PostConstruct
  public void initFakeUsers() {
    mathieu = new User("Mathieu", "BES", "pwdTest1", "matDu91@gmail.fr", "surLaA6", "06qlqchose");
    garik = new User("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose");
    florian = new User("Florian", "CARBONI", "pwdTest3", "bgDeL'IBGBI@gmail.fr", "versRis", "118 218");
    fawaz = new User("Fawaz", "MOUSSOUGAN", "pwdTest4", "whiteHatsMaster@gmail.fr", "unknown", "Call the police");

    users.add(mathieu);
    users.add(garik);
    users.add(florian);
    users.add(fawaz);
  }

  @PostConstruct
  public void initFakeParents() {
    parentMathieu = new Parent(mathieu, 10000);

    parents.add(parentMathieu);
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

}