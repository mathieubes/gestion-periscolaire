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
import api.models.activities.Activity;
import api.models.activities.restaurant.Allergen;
import api.models.enums.EnvKey;
import api.models.http.AllergenPostDTO;
import api.models.http.ChildPostDTO;
import api.models.http.SigninPostDTO;
import api.services.env.EnvGlobalUseService;

@Service
@Scope("singleton")
public class ParentService {
  private ArrayList<Parent> parents = new ArrayList<>();

  @Autowired
  ActivityService activityService;

  @PostConstruct
  private void initFakeParents() {
    addParent(new UserPostDTO("Mathieu", "BES", "pwdTest1", "matDu91@gmail.fr", "surLaA6", "06qlqchose"));
    addParent(new UserPostDTO("Garik", "DERMINJYAN", "pwdTest2", "gd@gmail.fr", "surLePeriph", "07qlqchose"));
    addParent(new UserPostDTO("Fawaz", "MOUSSOUGAN", "pwdTest4", "criminal@gmail.fr", "unknown", "Call the police"));

    final var florian = addParent(
        new UserPostDTO("Florian", "CARBONI", "pwdTest3", "test@test.fr", "versRis", "118 218"));
    florian.addChild(new ChildPostDTO("Pedro", "Italian", new Date(), Gender.MALE, true));
    florian.addChild(new ChildPostDTO("Sara", "Mexican", new Date(), Gender.FEMALE, false));
  }

  @PostConstruct
  private void initFakeCoefNumber() {
    Parent parentFlorian = this.getParentByEmail("test@test.fr");
    parentFlorian.getCoefNumber(15000);
  }

  // #region Get parents
  public ArrayList<Parent> getParents() {
    return parents;
  }
  // #endregion

  // #region Get parent by Id
  public Parent getParentByID(String id) {
    return this.parents.stream().filter(parent -> UUID.fromString(id).equals(parent.getId())).findFirst()
        .orElse(null);
  }
  // #endregion

  // #region Get parent by email
  public Parent getParentByEmail(String email) {
    return this.parents.stream().filter(parent -> email.equals(parent.getEmail())).findFirst()
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

  // #region Update existing parent
  public Parent updateParent(UserPostDTO userPostDTO, String id) {
    Parent parent = getParentByID(id);

    String saltedPassword = EnvGlobalUseService.getValue(EnvKey.SALT_HASH_KEY) + userPostDTO.getPassword();
    String hashedPassword = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();

    userPostDTO.setPassword(hashedPassword);

    parent.setPersonalInfo(userPostDTO);
    return parent;
  }
  // #endregion

  // #region Delete parent
  public void deleteParent(String _id) {
    UUID id = UUID.fromString(_id);
    this.parents.removeIf(parent -> (parent.getId().equals(id)));
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

  // #region Delete child
  public void deleteChild(String parentId, String childId) {
    Parent parent = this.getParentByID(parentId);
    parent.removeChild(childId);
  }
  // #endregion

  // #region Register child to activity
  public void registerChildToActivity(String parentId, String childId, String activityId) {
    Activity activity = this.activityService.getActivitybyId(activityId);
    Parent parent = this.getParentByID(parentId);
    Child child = parent.childById(childId);
    if (!this.isChildAlreadySuscribedToGivenActivity(child, activity.getId().toString()))
      child.registerToActivity(activity);
  }
  // #endregion

  // #region Unsuscribe child from activity
  public void unsuscribeChildFromActivity(String parentId, String childId, String activityId) {
    Activity activity = this.activityService.getActivitybyId(activityId);
    Parent parent = this.getParentByID(parentId);
    Child child = parent.childById(childId);
    child.unsubscribeFromActivity(activity);
  }
  // #endregion

  // #region Add allergen to child
  public void addAllergenToChild(String parentId, String childId, AllergenPostDTO allergenPostDTO) {
    Parent parent = this.getParentByID(parentId);
    Child child = parent.childById(childId);
    Allergen allergen = new Allergen(allergenPostDTO.getCode(), allergenPostDTO.getName());
    if (!this.isChildAlreadyHasGivenAllergen(child, allergen))
      child.addAllergen(allergen);
  }
  // #endregion

  // #region Remove allergen from child
  public void removeAllergenFromChild(String parentId, String childId, AllergenPostDTO allergenPostDTO) {
    Parent parent = this.getParentByID(parentId);
    Child child = parent.childById(childId);
    child.removeAllergen(new Allergen(allergenPostDTO.getCode(), allergenPostDTO.getName()));
  }
  // #endregion

  // #region Check if a child is already suscribed to an activity
  public boolean isChildAlreadySuscribedToGivenActivity(Child child, String activityId) {
    ArrayList<Activity> activities = child.getActivities();
    return activities.contains(this.activityService.getActivitybyId(activityId));
  }
  // #endregion

  // #region Check if a child already has an allergen
  public boolean isChildAlreadyHasGivenAllergen(Child child, Allergen allergen) {
    ArrayList<Allergen> allergens = child.getAllergens();
    return allergens.contains(allergen);
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

  // #region Get total expenses
  public double getTotalExpenses(String id) {
    Parent parent = this.getParentByID(id);
    if (parent.getAnnualIncome() == 0) {
      return -1;
    } else {
      Double sum = (double) 0;
      for (Child child : parent.getChildren()) {
        for (Activity activity : child.getActivities()) {
          sum += activity.getPrice();
        }
      }

      return (sum * parent.getCoefNumber()) / 100;
    }

  }
  // #endregion
}