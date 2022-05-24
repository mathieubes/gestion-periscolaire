package api.models.activities.restaurant;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import api.models.activities.Activity;

@Entity
@DiscriminatorValue("cafeteria")
public class Cafeteria extends Activity {
  
  /*@Column(name = "menu", length = 256, nullable = false)
   private Menu menu;*/


  //@ManyToOne @JoinColumn(name="id")

  // @Column(name = "menu_list", nullable = false)
  @ManyToMany
  @JoinTable(name = "menu_cafet", 
  joinColumns = @JoinColumn(name = "idActivity"), 
  inverseJoinColumns = @JoinColumn(name = "idMenu"))
  private List <Menu> menus = new ArrayList<>();

  public Cafeteria(UUID id, String name, Date date, int duration, int capacity, int minSupervisors, double price) {
    super(id, name, date, duration, capacity, minSupervisors, price);
    //this.menu = new Menu();
  }

  public List <Menu> getMenu() {
    return this.menus;
  }
}
