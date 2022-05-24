package api.models.activities.restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import api.models.activities.Activity;

public class Cafeteria extends Activity {
  private ArrayList<Menu> menus;

  public Cafeteria(UUID id, String name, Date date, int duration, int capacity, int minSupervisors, double price) {
    super(id, name, date, duration, capacity, minSupervisors, price);
    this.menus = new ArrayList<Menu>();
  }

  public ArrayList<Menu> getMenu() {
    return this.menus;
  }
}
