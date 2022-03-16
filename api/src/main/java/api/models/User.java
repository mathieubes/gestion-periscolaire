package api.models;

import java.util.UUID;

public abstract class User {
  private UUID id;
  private String firstname;
  private String lastname;

  // @JsonIgnore
  private String password;

  private String email;
  private String address;
  private String phoneNumber;

  public User(UUID id, String firstname, String lastname, String password, String email, String address,
      String phoneNumber) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = password;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
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

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

}