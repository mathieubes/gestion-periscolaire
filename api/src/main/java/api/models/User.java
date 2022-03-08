package api.models;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.google.common.hash.Hashing;

public class User {
  private UUID id;
  private String firstName;
  private String lastName;
  private String passWord;

  public User(String firstname, String lastname, String passWord) {
    this.id = UUID.randomUUID();
    this.firstName = firstname;
    this.lastName = lastname;
    this.passWord = Hashing.sha256().hashString(passWord, StandardCharsets.UTF_8).toString();

    System.out.println("hashed password: " + this.passWord);  }

  public UUID getId() {
    return id;
  }

  public String getFirstname() {
    return firstName;
  }

  public String getLastname() {
    return lastName;
  }
}