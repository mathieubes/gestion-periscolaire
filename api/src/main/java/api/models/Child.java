package api.models;

import java.util.ArrayList;
import java.util.Date;

import api.models.activities.Activity;
import api.models.activities.restaurant.Allergen;
import api.models.enums.SexeType;

public class Child {
  private String firstname;
  private String lastname;
  private Date birthDate;
  private SexeType sexe;
  private ArrayList<Activity> activities;
  private ArrayList<Allergen> allergens;

  public Child(String firstname, String lastname, Date birthDate, SexeType sexe) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthDate = birthDate;
    this.sexe = sexe;
    this.activities = new ArrayList<Activity>();
    this.allergens = new ArrayList<Allergen>();
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public SexeType getSexe() {
    return sexe;
  }

  public ArrayList<Activity> getActivities() {
    return this.activities;
  }

  public ArrayList<Allergen> getAllergens() {
    return this.allergens;
  }

}
