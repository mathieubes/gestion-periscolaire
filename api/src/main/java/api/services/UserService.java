package api.services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.PostConstruct;
import com.google.common.hash.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.Parent;
import api.models.enums.EnvKey;
import api.models.http.SigninPostDTO;
import api.models.http.UserPostDTO;
import api.services.env.EnvGlobalUseService;

@Service
@Scope("singleton")
public class UserService {
  @Autowired
  JsonService jsonService;

  private ArrayList<Parent> parents = new ArrayList<Parent>();

  @PostConstruct
  private void initFakeParents() {
    addParent(new UserPostDTO("Mathieu", "BES", "pwdTest1", "matDu91@gmail.fr", "surLaA6", "06qlqchose"));
    addParent(new UserPostDTO("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose"));
    addParent(new UserPostDTO("Florian", "CARBONI", "pwdTest3", "bgDeL'IBGBI@gmail.fr", "versRis", "118 218"));
    addParent(new UserPostDTO("Fawaz", "MOUSSOUGAN", "pwdTest4", "criminal@gmail.fr", "unknown", "Call the police"));
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

  public Parent addParent(UserPostDTO userPostDTO) {
    UUID id = UUID.randomUUID();

    String saltedPassword = EnvGlobalUseService.getValue(EnvKey.SALT_HASH_KEY) + userPostDTO.getPassword();
    String hashedPassword = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();

    Parent parent = new Parent(id, userPostDTO.getFirstname(), userPostDTO.getLastname(), hashedPassword,
        userPostDTO.getEmail(), userPostDTO.getAddress(), userPostDTO.getPhoneNumber());
    this.parents.add(parent);
    return parent;
  }

  public void deleteParent(String _id) {
    UUID id = UUID.fromString(_id);
    this.parents.removeIf(parent -> (parent.getId().equals(id)));
  }
  
  public float computeFiscalCoef(Parent parent, float income) {
    final var dependentChildrenCount = parent.getChilden().size();
    // Considerer le fait qu'il y a 2 parents
    var familyQuotient = income / dependentChildrenCount;
    familyQuotient -= 10085;

    return 0;
  }

  public boolean areSigninCredentialsCorrect(SigninPostDTO signinPostDTO) {
    return this.parents.stream().filter(parent -> {
      boolean isEmailEqual = parent.getEmail().equals(signinPostDTO.getEmail());

      String saltedPassword = EnvGlobalUseService.getValue(EnvKey.SALT_HASH_KEY) + signinPostDTO.getPassword();
      String hashedPassword = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();
      boolean isPasswordEqual = parent.getPassword().equals(hashedPassword);

      return (isEmailEqual && isPasswordEqual);
    }).findFirst().isPresent();
  }

}