package api.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.Parent;

@Service
@Scope("singleton")
public class FakeDatabaseService {
  private ArrayList<Parent> parents = new ArrayList<Parent>();

  @PostConstruct
  public void initFakeParents() {
    Parent mathieu = new Parent("Mathieu", "BES", "pwdTest1", "matDu91@gmail.fr", "surLaA6", "06qlqchose", 10000);
    Parent garik = new Parent("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose", 209);
    Parent florian = new Parent("Florian", "CARBONI", "pwdTest3", "bgDeL'IBGBI@gmail.fr", "versRis", "118 218", 22);
    Parent fawaz = new Parent("Fawaz", "MOUSSOUGAN", "pwdTest4", "criminal@gmail.fr", "unknown", "Call the police", 0);

    parents.add(mathieu);
    parents.add(garik);
    parents.add(florian);
    parents.add(fawaz);
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

}