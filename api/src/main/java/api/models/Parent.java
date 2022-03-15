package api.models;

import java.util.ArrayList;

public class Parent extends User {
  private float fiscalNumber;
  private ArrayList<Child> children;

  public Parent(String firstname, String lastname, String password, String email, String address, String phoneNumber,
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
