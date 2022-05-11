package api.models.http;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import api.models.enums.Gender;

public class ChildPostDTO {
  @NotNull
  @NotBlank
  private String firstname;

  @NotNull
  @NotBlank
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
