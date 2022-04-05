package api.services;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.PostConstruct;

import api.models.Child;
import api.models.enums.Gender;
import api.models.http.UserPostDTO;

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
    final var florian = addParent(new UserPostDTO("Florian", "CARBONI", "pwdTest3", "bgDeL'IBGBI@gmail.fr", "versRis", "118 218"));
    florian.addDependentChild(new Child("Garrickc", "Italien", new Date(), Gender.FEMALE));
    florian.addDependentChild(new Child("Garricka", "Italienne", new Date(), Gender.MALE));
    addParent(new UserPostDTO("Fawaz", "MOUSSOUGAN", "pwdTest4", "criminal@gmail.fr", "unknown", "Call the police"));
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

  public Parent getParentByID(String uuid) {
    return this.parents.stream().filter(parent -> UUID.fromString(uuid).equals(parent.getId())).findFirst().orElse(null);
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
  
  // Formule utilisée par la mairie d'Evry
  // Round((case  when (greatest(2000/0.9/12, 551*(1.5+0.3* (2))+0.61*2000/0.9/12)-(1.5+0.3*(2))*240 )/(2+(2+(0*0.5))) >= 1300 then 0.8    when (greatest(2000/0.9/12, 551*(1.5+0.3*(2))+0.61*2000/0.9/12)-(1.5+0.3*(2))*240 )/(2+(2+(0*0.5))) >= 418 then 0.175 + 0.625/1300 * (greatest(2000/0.9/12, 551*(1.5+0.3*(2))+0.61*2000/0.9/12)-(1.5+0.3*(2))*240 )/(2+(2+(0*0.5)))  when (greatest(2000/0.9/12, 551*(1.5+0.3*(2))+0.61*2000/0.9/12)-(1.5+0.3*(2))*240 )/(2+(2+(0*0.5))) >=200 then -0.022 +(0.197/418 + 0.625/1300) * (greatest(2000/0.9/12, 551*(1.5+0.3*(2))+0.61*2000/0.9/12)-(1.5+0.3*2)*240 )/(2+(2+(0*0.5)))  else 0.168 end) ,3)*100
  public double computeFiscalCoef(Parent parent, double annualIncome) {
    if (parent != null) {
      final var dependChildrenCount = parent.getDependentChildrenCount();
      double coef = 0;
      double calcul = (Math.max(annualIncome / 0.9 / 12, 551*(1.5+0.3 * dependChildrenCount) + 0.61 * annualIncome / 0.9 / 12) - (1.5+0.3*dependChildrenCount) * 240) / (2 + (dependChildrenCount));

      if (calcul >= 1300) coef = 0.8f;
      else if (calcul >= 418) coef = 0.175 + 0.625/1300 * calcul;
      else if (calcul >= 200) coef = -0.022 +(0.197/418 + 0.625/1300) * calcul;
      else coef = 0.168;

      coef *= 100;

      DecimalFormat df = new DecimalFormat("00.00");
      return Double.parseDouble(df.format(coef));
    } else return 0;
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