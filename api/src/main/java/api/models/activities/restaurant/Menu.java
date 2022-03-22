package api.models.activities.restaurant;

import java.util.ArrayList;

public class Menu {
  private ArrayList<Aliment> aliments;

  public Menu(ArrayList<Aliment> aliments) {
    this.aliments = aliments;
  }

  public Menu() {

  }

  public ArrayList<Aliment> getAliments() {
    return this.aliments;
  }
}
