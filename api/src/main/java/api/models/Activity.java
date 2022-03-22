package api.models;

import java.util.Date;
import java.util.UUID;

public class Activity {
  private UUID id;
  private String name;
  private Date date;
  private Number duration;
  private Number capacity;
  private Number minSupervisors;
  private Number price;

  public Activity(UUID id, String name, Date date, Number duration, Number capacity, Number minSupervisors,
      Number price) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.duration = duration;
    this.capacity = capacity;
    this.minSupervisors = minSupervisors;
    this.price = price;
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

  public Number getDuration() {
    return this.duration;
  }

  public Number getCapacity() {
    return this.capacity;
  }

  public Number getMinSupervisors() {
    return this.minSupervisors;
  }

  public Number getPrice() {
    return this.price;
  }

}
