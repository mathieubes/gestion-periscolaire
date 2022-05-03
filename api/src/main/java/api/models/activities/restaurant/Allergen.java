package api.models.activities.restaurant;
import java.util.List;

import javax.persistence.*;
import api.models.*;

@Entity
@Table(name = "allergens")
public class Allergen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String code;

  @Column(name = "firstName", length = 256, nullable = false)
  private String name;

 //@ManyToMany(mappedBy = "allergens", cascade = CascadeType.ALL)
 @ManyToMany
  @JoinTable(name = "childallergens", 
  joinColumns = @JoinColumn(name = "code"), 
  inverseJoinColumns = @JoinColumn(name = "idChild"))
 private List<Child> childAllergens;

 //@ManyToMany(mappedBy = "listAllergens", cascade = CascadeType.ALL)

 @ManyToMany
  @JoinTable(name = "contenance_Aliment", 
  joinColumns = @JoinColumn(name = "id_Allergen"), 
  inverseJoinColumns = @JoinColumn(name = "id_Aliment"))
  private List<Aliment> listAliment;


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
