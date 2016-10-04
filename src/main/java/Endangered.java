import org.sql2o.*;
import java.util.List;

public class Endangered extends Sighting {
  private int age;
  private int health;

  public Endangered(int personId, int animalId, int locationId, int age, int health) {
    super(personId, animalId, locationId);
    if(age>4||age<1) {
      throw new IllegalArgumentException("Endangered animals require an age");
    } else {
      this.age=age;
    }
    if(health>5||health<1) {
      throw new IllegalArgumentException("Endangered animals require a health status");
    } else {
      this.health = health;
    }
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalId, locationId, personId, time, age, health) VALUES (:animalId, :locationId, :personId, now(), :age, :health)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("locationId", this.locationId)
        .addParameter("personId", this.personId)
        .addParameter("age", this.age)
        .addParameter("health", this.health)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age=age;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE sightings SET age = :age WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health=health;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE sightings SET health = :health WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("health", health)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
