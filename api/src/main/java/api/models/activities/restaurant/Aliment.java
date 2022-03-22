package api.models.activities.restaurant;

import java.util.ArrayList;

import api.models.enums.MealType;

public class Aliment {
  private String name;
  private ArrayList<Allergen> allergens;
  private MealType mealType;

  public Aliment(String name, ArrayList<Allergen> allergens, MealType mealType) {
    this.name = name;
    this.allergens = allergens;
    this.mealType = mealType;
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Allergen> getAllergens() {
    return this.allergens;
  }

  public MealType getMealType() {
    return this.mealType;
  }
}
