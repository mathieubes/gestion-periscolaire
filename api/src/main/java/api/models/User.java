package api.models;

import java.util.UUID;

public class User {
  private UUID id;
  private String firstname;
  private String lastname;

  public User(String firstname, String lastname) {
    this.id = UUID.randomUUID();
    this.firstname = firstname;
    this.lastname = lastname;
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