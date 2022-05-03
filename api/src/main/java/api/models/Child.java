package api.models;

import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Set;
import java.util.List;

import api.models.activities.*;
import api.models.activities.restaurant.Allergen;
import api.models.enums.Gender;

@Entity
@Table(name = "childs")
public class Child {
   
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID idChild;
  
  @Column(name = "firstName", length = 256, nullable = false)
  private String firstname;

  @Column(name = "lastName", length = 256, nullable = false)
  private String lastname;

  @Temporal(value=TemporalType.DATE)
  @Column(name = "date_of_birth", nullable = false)
  private Date birthDate;

  @Column(name = "Gender")
  private Gender gender;

  @ManyToOne
  @JoinColumn(name= "parent_id", nullable = false)
  private Parent parent;

  @Column(name = "activity_list", nullable = false)
  @ManyToMany
  @JoinTable(name = "child_activities", 
  joinColumns = @JoinColumn(name = "idChild"), 
  inverseJoinColumns = @JoinColumn(name = "idActivity"))
  private List <Activity> activities;
  
  @Column(name = "allergen_list", nullable = false)
  @ManyToMany
  @JoinTable(name = "childallergens", 
  joinColumns = @JoinColumn(name = "idChild"), 
  inverseJoinColumns = @JoinColumn(name = "code"))
  private List<Allergen> childAllergens;

  public Child(){};

  public Child(String firstname, String lastname, Date birthDate, Gender gender) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthDate = birthDate;
    this.gender = gender;
    this.activities = new ArrayList<Activity>();
    this.childAllergens = new ArrayList<Allergen>();
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

  public Gender getSexe() {
    return gender;
  }

  public List<Activity> getActivities() {
    return this.activities;
  }

  public List<Allergen> getAllergens() {
    return this.childAllergens;
  }

}
