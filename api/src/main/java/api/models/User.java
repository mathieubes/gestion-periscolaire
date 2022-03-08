package api.models;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.hash.Hashing;

public class User {
  @JsonIgnore
  private UUID id;
  
  private String firstname;
  private String lastname;

  @JsonIgnore
  private String password;

  public User(String firstname, String lastname, String passWord) {
    this.id = UUID.randomUUID();
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = Hashing.sha256().hashString(passWord, StandardCharsets.UTF_8).toString();
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
}