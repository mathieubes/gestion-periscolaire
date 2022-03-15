package api.models;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.hash.Hashing;

public abstract class User {
  private UUID id;
  private String firstname;
  private String lastname;

  @JsonIgnore
  private String password;

  private String email;
  private String address;
  private String phoneNumber;

  public User(String firstname, String lastname, String password, String email, String address, String phoneNumber) {
    this.id = UUID.randomUUID();
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
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