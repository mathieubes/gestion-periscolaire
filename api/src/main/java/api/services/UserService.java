package api.services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.google.common.hash.Hashing;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.Parent;
import api.models.enums.EnvKey;
import api.services.env.EnvGlobalUseService;

@Service
@Scope("singleton")
public class UserService {
  private ArrayList<Parent> parents = new ArrayList<Parent>();

  @PostConstruct
  private void initFakeParents() {
    addParent("Mathieu", "BES", "pwdTest1", "matDu91@gmail.fr", "surLaA6", "06qlqchose", 10000);
    addParent("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose", 209);
    addParent("Florian", "CARBONI", "pwdTest3", "bgDeL'IBGBI@gmail.fr", "versRis", "118 218", 22);
    addParent("Fawaz", "MOUSSOUGAN", "pwdTest4", "criminal@gmail.fr", "unknown", "Call the police", 99999);
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

  public void addParent(String firstname, String lastname, String password, String email, String address,
      String phoneNumber, float fiscalNumber) {

    UUID id = UUID.randomUUID();

    String saltedPassword = EnvGlobalUseService.getValue(EnvKey.SALT_HASH_KEY) + password;
    String hashedPassword = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();

    Parent parent = new Parent(id, firstname, lastname, hashedPassword, email, address, phoneNumber, fiscalNumber);
    this.parents.add(parent);
  }

}