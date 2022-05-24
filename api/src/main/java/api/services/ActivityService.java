package api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.activities.Activity;
import api.models.activities.restaurant.Cafeteria;
import api.models.activities.sport.Sport;
import api.models.enums.Difficulty;
import api.models.http.ActivityPostDTO;

@Service
@Scope("singleton")
public class ActivityService {
  private ArrayList<Activity> activities = new ArrayList<>();

  @PostConstruct
  private void initFakeActivities() {
    addActivity(new ActivityPostDTO("Test activity", new Date(), 60, 20, 1, 25.0));
    addSport(new ActivityPostDTO("Football", new Date(), 120, 22, 2, 30), 8, Difficulty.BEGINNER);
    addCafeteria(new ActivityPostDTO("Cafeteria", new Date(), 60, 150, 5, 5));
  }

  public Activity addActivity(ActivityPostDTO activityPostDTO) {
    UUID id = UUID.randomUUID();

    Activity activity = new Activity(id, activityPostDTO);
    this.activities.add(activity);
    return activity;
  }

  public Sport addSport(ActivityPostDTO activityPostDTO, int requiredAge, Difficulty difficulty) {
    UUID id = UUID.randomUUID();
    Sport sport = new Sport(id, activityPostDTO, requiredAge, difficulty);
    this.activities.add(sport);
    return sport;
  }

  public Cafeteria addCafeteria(ActivityPostDTO activityPostDTO) {
    UUID id = UUID.randomUUID();

    Cafeteria cafeteria = new Cafeteria(id, activityPostDTO);
    // ArrayList<Menu> menus1 = new ArrayList<>();
    // ArrayList<Aliment> aliments1 = new ArrayList<>();

    this.activities.add(cafeteria);
    return cafeteria;
  }

  public ArrayList<Activity> getActivities() {
    return this.activities;
  }

  public Activity getActivitybyId(String id) {
    return this.activities.stream().filter(parent -> UUID.fromString(id).equals(parent.getId())).findFirst()
        .orElse(null);
  }
}
