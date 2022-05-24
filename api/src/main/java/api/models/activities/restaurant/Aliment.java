package api.models.activities.restaurant;


import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import api.models.enums.MealType;

@Entity
@Table(name = "aliments")
public class Aliment {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id_Aliment;

  @Column(name = "name_Aliment", length = 256, nullable = false)
  private String name;
 
  @ManyToMany
  @JoinTable(name = "contenance_Aliment", 
  joinColumns = @JoinColumn(name = "id_Aliment"), 
  inverseJoinColumns = @JoinColumn(name = "code"))
  private List <Allergen> listAllergens;


  @ManyToMany
  @JoinTable(name = "contenance_menu",
  joinColumns = @JoinColumn(name = "id_Aliment"), 
  inverseJoinColumns = @JoinColumn(name = "idMenu"))
  private List <Menu> listMenus;

 
  @Enumerated(EnumType.STRING)
  @Column(length = 24)
  private MealType mealType;

  public Aliment(/*UUID id*/ String name, /*ArrayList<Allergen> allergens,*/ MealType mealType) {
    /*this.id=id;*/
    this.name = name;
   /* this.listAllergens =new ArrayList<>();*/
    this.mealType = mealType;
  }

  public String getName() {
    return this.name;
  }

  public List<Allergen> getAllergens() {
    return this.listAllergens;
  }

  public MealType getMealType() {
    return this.mealType;
  }
}
