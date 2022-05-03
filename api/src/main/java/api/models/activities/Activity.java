package api.models.activities;

import java.util.Date;
import java.util.UUID;

import api.models.http.ActivityPostDTO;

public class Activity {
  private UUID id;
  private String name;
  private Date date;
  private int duration;
  private int capacity;
  private int minSupervisors;
  private double price;

  public Activity(UUID id, String name, Date date, int duration, int capacity, int minSupervisors,
      double price) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.duration = duration;
    this.capacity = capacity;
    this.minSupervisors = minSupervisors;
    this.price = price;
  }

  public Activity(UUID id, ActivityPostDTO activityPostDTO) {
    this.id = id;
    this.name = activityPostDTO.getName();
    this.date = activityPostDTO.getDate();
    this.duration = activityPostDTO.getDuration();
    this.capacity = activityPostDTO.getCapacity();
    this.minSupervisors = activityPostDTO.getMinSupervisors();
    this.price = activityPostDTO.getPrice();
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Date getDate() {
    return this.date;
  }

  public int getDuration() {
    return this.duration;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public int getMinSupervisors() {
    return this.minSupervisors;
  }

  public double getPrice() {
    return this.price;
  }

}
