package api.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import api.models.activities.Activity;
import api.models.activities.restaurant.Allergen;
import api.models.enums.Gender;
import api.models.http.ChildPostDTO;

public class Child {
  private UUID id;
  private String firstname;
  private String lastname;
  private Date birthDate;
  private Gender gender;
  private boolean dependent;

  private ArrayList<Activity> activities;
  private ArrayList<Allergen> allergens;

  public Child(UUID id, ChildPostDTO childPostDTO) {
    this.id = id;
    this.firstname = childPostDTO.getFirstname();
    this.lastname = childPostDTO.getLastname();
    this.birthDate = childPostDTO.getBirthDate();
    this.gender = childPostDTO.getGender();
    this.dependent = childPostDTO.getDependent();
    this.activities = new ArrayList<Activity>();
    this.allergens = new ArrayList<Allergen>();
  }

  public UUID getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public Gender getGender() {
    return gender;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public boolean isDependent() {
    return dependent;
  }

  public ArrayList<Activity> getActivities() {
    return this.activities;
  }

  public ArrayList<Allergen> getAllergens() {
    return this.allergens;
  }

  public void registerToActivity(Activity activity) {
    this.activities.add(activity);
  }

  public void unsubscribeFromActivity(Activity activity) {
    this.activities.removeIf(el -> el.getId().equals(activity.getId()));
  }

  public Child setPersonalInfo(ChildPostDTO childPostDTO) {
    this.firstname = childPostDTO.getFirstname();
    this.lastname = childPostDTO.getLastname();
    this.birthDate = childPostDTO.getBirthDate();
    this.gender = childPostDTO.getGender();
    this.dependent = childPostDTO.getDependent();

    return this;
  }

  public void addAllergen(Allergen allergen) {
    this.allergens.add(allergen);
  }

  public void removeAllergen(Allergen allergen) {
    this.allergens.removeIf(el -> el.getCode().equals(allergen.getCode()) && el.getName().equals(allergen.getName()));
  }
}
