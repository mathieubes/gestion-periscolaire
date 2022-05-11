package api.models.http;

import java.util.Date;

public class ActivityPostDTO {
  private String name;
  private Date date;
  private int duration;
  private int capacity;
  private int minSupervisors;
  private double price;

  public ActivityPostDTO(String name, Date date, int duration, int capacity, int minSupervisors,
      double price) {
    this.name = name;
    this.date = date;
    this.duration = duration;
    this.capacity = capacity;
    this.minSupervisors = minSupervisors;
    this.price = price;
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
