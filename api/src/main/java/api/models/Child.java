package api.models;

import java.util.Date;

import api.models.enums.SexeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;


@Entity
@Table(name = "CHILD")
public class Child {

  @Column(name = "firstName", length = 256, nullable = false)
  private String firstname;
  
  @Column(name = "lastName", length = 256, nullable = false)
  private String lastname;
  
  @Temporal(value=TemporalType.Date)
  @Column(name = "date_of_birth", nullable = false)
  private Date birthDate;
  
  @Column(name = "sex")
  private SexeType sexe;

  @ManyToOne
  @JoinColumn(name= "parent_id", nullable = false)
  private Parent parent;

  public Child (String firstname, String lastname, Date birthDate, SexeType sexe) {
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
