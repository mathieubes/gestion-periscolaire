package api.models.activities.sport;

import java.util.Date;
import java.util.UUID;

import api.models.activities.Activity;
import api.models.enums.Difficulty;

public class Sport extends Activity {
  private Number requiredAge;
  private Difficulty difficulty;

  public Sport(UUID id, String name, Date date, Number duration, Number capacity, Number minSupervisors,
      Number price, Number requiredAge, Difficulty difficulty) {
    super(id, name, date, duration, capacity, minSupervisors, price);

    this.requiredAge = requiredAge;
    this.difficulty = difficulty;
  }

  public Number getRequiredAge() {
    return this.requiredAge;
  }

  public Difficulty getDifficulty() {
    return this.difficulty;
  }
}
