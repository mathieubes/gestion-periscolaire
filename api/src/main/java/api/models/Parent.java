package api.models;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PARENT")
public class Parent extends User {



  @Column(name = "fiscalNumber", nullable = false)
  private float fiscalNumber;
  
  @Column(name = "child_list", nullable = false)
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  private ArrayList<Child> children;

  public Parent (String firstname, String lastname, String password, String email, String address, String phoneNumber,
      float fiscalNumber) {
    super(firstname, lastname, password, email, address, phoneNumber);

    this.fiscalNumber = fiscalNumber;
    this.children = new ArrayList<Child>();
  }

  public float getFiscalNumber() {
    return fiscalNumber;
  }

  public ArrayList<Child> getChilden() {
    return children;
  }
}                             
