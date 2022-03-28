package api.models.activities.restaurant;

public class Allergen {
  private String code;
  private String name;

  public Allergen(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
