package api.models;

import java.util.ArrayList;
import java.util.UUID;

public class Parent extends User {
  private float fiscalNumber;
  private ArrayList<Child> children;

  public Parent(UUID id, String firstname, String lastname, String password, String email, String address,
      String phoneNumber) {
    super(id, firstname, lastname, password, email, address, phoneNumber);

    this.fiscalNumber = 0;
    this.children = new ArrayList<>();
  }

  public float getFiscalNumber() {
    return fiscalNumber;
  }

  public ArrayList<Child> getChildren() {
    return children;
  }

  public void addChild(Child child) {
    this.children.add(child);
  }

  public int calculateDependentChildrenCount() {
    return (int) this.children.stream().filter(child -> {
      return child.isDependent();
    }).count();
  }

}
