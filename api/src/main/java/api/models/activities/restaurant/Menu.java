package api.models.activities.restaurant;
import javax.persistence.*;

// import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Menu")
public class Menu {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID idMenu;

  @ManyToMany
  @JoinTable(name = "contenance_menu", 
  joinColumns = @JoinColumn(name = "idMenu"), 
  inverseJoinColumns = @JoinColumn(name = "id"))
  private List<Aliment> aliments;



  // @OneToMany(targetEntity = Cafeteria.class, mappedBy = "cafeteria")
  // @Column(name = "cafet_list", nullable = false)
  @ManyToMany
  @JoinTable(name = "menu_cafet", 
  joinColumns = @JoinColumn(name = "idMenu"), 
  inverseJoinColumns = @JoinColumn(name = "idActivity"))
  private List<Cafeteria> listCafet ;//= new ArrayList<>();


  public Menu(List<Aliment> aliments) {
    this.aliments = aliments;
    this.idMenu = UUID.randomUUID();
  }

  public Menu() {

  }

  public List<Aliment> getAliments() {
    return this.aliments;
  }

  public UUID getId() {
    return this.idMenu;
  }
}
