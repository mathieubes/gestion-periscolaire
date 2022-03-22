package api.models.activities.restaurant;

import java.util.Date;
import java.util.UUID;

import api.models.activities.Activity;

public class Cafeteria extends Activity {
  private Menu menu;

  public Cafeteria(UUID id, String name, Date date, Number duration, Number capacity, Number minSupervisors,
      Number price) {
    super(id, name, date, duration, capacity, minSupervisors, price);
    this.menu = new Menu();
  }

  public Menu getMenu() {
    return this.menu;
  }
}
