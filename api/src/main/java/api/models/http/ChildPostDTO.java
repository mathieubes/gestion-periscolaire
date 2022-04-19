package api.models.http;

import java.util.Date;

import api.models.enums.Gender;

public class ChildPostDTO {
  private String firstname;
  private String lastname;
  private Date birthDate;
  private Gender gender;
  private boolean dependent;

  public ChildPostDTO(String firstname, String lastname, Date birthDate, Gender gender, boolean dependent) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthDate = birthDate;
    this.gender = gender;
    this.dependent = dependent;
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

  public Gender getGender() {
    return gender;
  }

  public boolean getDependent() {
    return dependent;
  }
}
