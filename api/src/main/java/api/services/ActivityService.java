package api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import api.models.activities.Activity;
import api.models.http.ActivityPostDTO;

@Service
@Scope("singleton")
public class ActivityService {
  private ArrayList<Activity> activities = new ArrayList<>();

  @PostConstruct
  private void initFakeActivities() {
    addActivity(new ActivityPostDTO("Test activity", new Date(), 60, 20, 1, 25.0));
    addActivity(new ActivityPostDTO("Football", new Date(), 120, 22, 2, 30));
  }

  public Activity addActivity(ActivityPostDTO activityPostDTO) {
    UUID id = UUID.randomUUID();

    Activity activity = new Activity(id, activityPostDTO);
    this.activities.add(activity);
    return activity;
  }

  public ArrayList<Activity> getActivities() {
    return this.activities;
  }

  public Activity getActivitybyId(String id) {
    return this.activities.stream().filter(parent -> UUID.fromString(id).equals(parent.getId())).findFirst()
        .orElse(null);
  }
}
