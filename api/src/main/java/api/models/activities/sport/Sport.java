package api.models.activities.sport;

import java.util.Date;
import java.util.UUID;

import api.models.activities.Activity;
import api.models.enums.Difficulty;
import api.models.http.ActivityPostDTO;

public class Sport extends Activity {
  private int requiredAge;
  private Difficulty difficulty;

  public Sport(UUID id, String name, Date date, int duration, int capacity, int minSupervisors,
      double price, int requiredAge, Difficulty difficulty) {
    super(id, name, date, duration, capacity, minSupervisors, price);

    this.requiredAge = requiredAge;
    this.difficulty = difficulty;
  }

  public Sport(UUID id, ActivityPostDTO activityPostDTO, int requiredAge, Difficulty difficulty) {
    super(id, activityPostDTO);

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
