package api.models;

import java.util.Date;

import api.models.enums.SexeType;

public class Child {
  private String firstname;
  private String lastname;
  private Date birthDate;
  private SexeType sexe;

  public Child(String firstname, String lastname, Date birthDate, SexeType sexe) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthDate = birthDate;
    this.sexe = sexe;
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
}
