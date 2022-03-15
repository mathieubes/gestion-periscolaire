package api.models;

public class Parent extends User {
  private float fiscalNumber;

  public Parent(String firstname, String lastname, String password, String email, String address, String phoneNumber,
      float fiscalNumber) {
    super(firstname, lastname, password, email, address, phoneNumber);
    this.fiscalNumber = fiscalNumber;
  }

  public Parent(User user, float fiscalNumber) {
    super(user.getFirstname(), user.getLastname(), user.getPassword(), user.getEmail(), user.getAddress(),
        user.getPhoneNumber());
    this.fiscalNumber = fiscalNumber;
  }

  public float getFiscalNumber() {
    return fiscalNumber;
  }
}
