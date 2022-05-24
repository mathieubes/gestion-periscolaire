package api.models.activities.sport;

import java.util.Date;
// import java.util.UUID;
import javax.persistence.*;
import api.models.activities.Activity;
import api.models.enums.Difficulty;

@Entity
@DiscriminatorValue("Sport")
public class Sport extends Activity {

  @Column(name = "age", length = 256, nullable = false)
  private int requiredAge;

  @Enumerated(EnumType.STRING)
  @Column(length = 24)
  private Difficulty difficulty;

  public Sport(/*UUID id*/ String name, Date date, int duration, int capacity, int minSupervisors,
      double price, int requiredAge, Difficulty difficulty) {
    super(/*id*/ name, date, duration, capacity, minSupervisors, price);

    this.requiredAge = requiredAge;
    this.difficulty = difficulty;
  }

  public int getRequiredAge() {
    return this.requiredAge;
  }

  public Difficulty getDifficulty() {
    return this.difficulty;
  }
}
