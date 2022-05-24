package api.models;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_PERSONNE")
@Table(name = "users")
public abstract class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(name = "firstName", length = 256, nullable = false)
  private String firstname;

  @Column(name = "lastName", length = 256, nullable = false)
  private String lastname;

  // @JsonIgnore
  @Column(name = "password", length = 256, nullable = false)
  private String password;
  
  @Column(name = "email", length = 150, nullable = false)
  private String email;

  @Column(name = "adress", length = 256, nullable = false)
  private String address;

  @Column(name = "Phone_Number", length = 256, nullable = false)
  private String phoneNumber;
  
  public User(){};

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

  public void setFirstname (String firstname) {
       this.firstname = firstname;
  }

  public void setLastname (String lastname) {
    this.lastname = lastname;
}

public void setAddress (String address) {
  this.address = address;
}

  @Override
  public String toString() {
      return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ",email="+ email +", address=" + address + ",  email=" + email
     + "]";
  }


}