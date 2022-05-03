package api.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;


@Entity
@Table(name = "parents")
public class Parent extends User {

  @Column(name = "fiscalNumber", nullable = false)
  private float fiscalNumber;


  @Column(name = "child_list", nullable = false)
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private List<Child> children;

  public Parent(){};

  public Parent(UUID id, String firstname, String lastname, String password, String email, String address,
      String phoneNumber) {
    super(id, firstname, lastname, password, email, address, phoneNumber);

    this.fiscalNumber = 0;
    this.children = new ArrayList<Child>();
  }



  public float getFiscalNumber() {
    return fiscalNumber;
  }

  public List<Child> getChildren() {
    return children;
  }

  public void setFiscalNumber(float fiscalNumber) {
    this.fiscalNumber = fiscalNumber;
  }
 
}
