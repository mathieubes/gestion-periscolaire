package api.models.activities.restaurant;

import java.util.ArrayList;
import java.util.UUID;

public class Menu {
  private ArrayList<Aliment> aliments;
  private UUID id;

  public Menu(ArrayList<Aliment> aliments) {
    this.aliments = aliments;
    this.id = UUID.randomUUID();
  }

  public ArrayList<Aliment> getAliments() {
    return this.aliments;
  }

  public UUID getId() {
    return this.id;
  }
}
