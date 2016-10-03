import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Sighting {
  protected int id;
  protected int personId;
  protected int animalId;
  protected int locationId;
  protected Timestamp time;

  public Sighting(int personId, int animalId, int locationId) {
    this.personId = personId;
    this.animalId = animalId;
    this.locationId = locationId;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalId, locationId, personId, time) VALUES (:animalId, :locationId, :personId, now())";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("locationId", this.locationId)
        .addParameter("personId", this.personId)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    String sql = "SELECT * FROM sightings ORDER BY time ASC";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByLocation(int id) {
    String sql = "SELECT * FROM sightings WHERE locationid = :id ORDER BY time ASC";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByAnimal(int id) {
    String sql = "SELECT * FROM sightings WHERE animalid = :id ORDER BY time ASC";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByRanger(int id) {
    String sql = "SELECT * FROM sightings WHERE personid = :id ORDER BY time ASC";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allAnimals() {
    //String sql = "SELECT animals.type FROM sightings JOIN animals ON (sightings.animalId=animals.id) WHERE NOT animal.type = 3 ORDER BY time DESC";
    String sql = "SELECT * FROM sightings ORDER BY time DESC";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }


  public static List<Sighting> allPokemon() {
    //String sql = "SELECT animals.type FROM sightings JOIN animals ON (sightings.animalId=animals.id) WHERE animal.type = 3 ORDER BY time DESC";
    String sql = "SELECT * FROM sightings ORDER BY time DESC";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  // getters and setters for each property
  public int getId() {
    return this.id;
  }

  public String getTime() {
    String strTime = new SimpleDateFormat("MMMMM dd, yyyy HH:mm").format(time);
    return strTime;
  }

  public int getAnimalId() {
    return this.animalId;
  }

  public String getAnimal() {
    return Animal.find(animalId).getName();
  }

  public int getLocationId() {
    return this.locationId;
  }

  public String getLocation() {
    return Location.find(locationId).getDescription();
  }

  public int getPersonId() {
    return this.personId;
  }

  public String getRanger() {
    return "Ranger " + Ranger.find(personId).getFirstName() + " " + Ranger.find(personId).getLastName();
  }

}
