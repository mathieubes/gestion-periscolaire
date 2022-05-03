package api.models.activities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.*;
import api.models.Child;



@Entity
@Table(name = "Activities")
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID idActivity;
  
  @Column(name = "name", length = 256, nullable = false)
  private String name;
  
  @Temporal(value=TemporalType.DATE)
  @Column(name = "date", nullable = false)
  private Date date;
  
  @Column(name="duration", length = 256, nullable = false)
  private int duration;

  @Column(name="capacity", length = 256, nullable = false)
  private int capacity;
  
  @Column(name="supervisors", length = 256, nullable = false)
  private int minSupervisors;

  @Column(name="price", length = 256, nullable = false)
  private double price;

  @ManyToMany(mappedBy = "activities", cascade = CascadeType.ALL)
  private List<Child> childPractics;

  public Activity(UUID id, String name, Date date, int duration, int capacity, int minSupervisors,
      double price) {
    this.idActivity = id;
    this.name = name;
    this.date = date;
    this.duration = duration;
    this.capacity = capacity;
    this.minSupervisors = minSupervisors;
    this.price = price;
  }

  public UUID getId() {
    return this.idActivity;
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
