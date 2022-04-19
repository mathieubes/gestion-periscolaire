package api.services;

import java.nio.charset.StandardCharsets;
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
import api.models.http.ChildPostDTO;
import api.models.http.SigninPostDTO;
import api.services.env.EnvGlobalUseService;

@Service
@Scope("singleton")
public class UserService {
  @Autowired
  JsonService jsonService;

  private ArrayList<Parent> parents = new ArrayList<>();

  @PostConstruct
  private void initFakeParents() {
    addParent(new UserPostDTO("Mathieu", "BES", "pwdTest1", "matDu91@gmail.fr", "surLaA6", "06qlqchose"));
    addParent(new UserPostDTO("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose"));
    addParent(new UserPostDTO("Fawaz", "MOUSSOUGAN", "pwdTest4", "criminal@gmail.fr", "unknown", "Call the police"));

    final var florian = addParent(
        new UserPostDTO("Florian", "CARBONI", "pwdTest3", "bgDeL'IBGBI@gmail.fr", "versRis", "118 218"));
    florian.addChild(new ChildPostDTO("Pedro", "Italian", new Date(), Gender.MALE, true));
    florian.addChild(new ChildPostDTO("Sara", "Mexican", new Date(), Gender.FEMALE, false));
  }

  // #region Get parents
  public ArrayList<Parent> getParents() {
    return parents;
  }
  // #endregion

  // #region Get parent by Id
  public Parent getParentByID(String uuid) {
    return this.parents.stream().filter(parent -> UUID.fromString(uuid).equals(parent.getId())).findFirst()
        .orElse(null);
  }
  // #endregion

  // #region Add parent
  public Parent addParent(UserPostDTO userPostDTO) {
    UUID id = UUID.randomUUID();

    String saltedPassword = EnvGlobalUseService.getValue(EnvKey.SALT_HASH_KEY) + userPostDTO.getPassword();
    String hashedPassword = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();

    Parent parent = new Parent(id, userPostDTO.getFirstname(), userPostDTO.getLastname(), hashedPassword,
        userPostDTO.getEmail(), userPostDTO.getAddress(), userPostDTO.getPhoneNumber());
    this.parents.add(parent);
    return parent;
  }
  // #endregion

  // #region Add child
  public ArrayList<Child> addChild(ChildPostDTO childPostDTO, String id) {
    Parent parent = this.getParentByID(id);
    parent.addChild(childPostDTO);
    return parent.getChildren();
  }
  // #endregion

  // #region Update existing child
  public Child updateChild(ChildPostDTO childPostDTO, String parentId, String childId) {
    Parent parent = getParentByID(parentId);
    Child child = parent.childById(childId);
    child.setPersonalInfo(childPostDTO);
    return child;
  }
  // #endregion

  // #region Delete parent
  public void deleteParent(String _id) {
    UUID id = UUID.fromString(_id);
    this.parents.removeIf(parent -> (parent.getId().equals(id)));
  }
  // #endregion

  // #region Signin
  public Optional<Parent> areSigninCredentialsCorrect(SigninPostDTO signinPostDTO) {
    Optional<Parent> parentFound = this.parents.stream().filter(parent -> {
      boolean isEmailEqual = parent.getEmail().equals(signinPostDTO.getEmail());

      String saltedPassword = EnvGlobalUseService.getValue(EnvKey.SALT_HASH_KEY) + signinPostDTO.getPassword();
      String hashedPassword = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();
      boolean isPasswordEqual = parent.getPassword().equals(hashedPassword);

      return (isEmailEqual && isPasswordEqual);
    }).findFirst();

    return parentFound;
  }
  // #endregion

}