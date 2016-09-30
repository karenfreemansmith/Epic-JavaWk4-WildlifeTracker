import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

public class Sighting  implements DatabaseManagement {
  private int id;
  private int personId;
  private int animalId;
  private int locationId;

  public Sighting(int personId, int animalId, int locationId) {
    this.personId = personId;
    this.animalId = animalId;
    this.locationId = locationId;
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (personId, animalId, locationId, time) VALUES personID, :animalId, :locationId, now())";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("personId", this.personId)
        .addParameter("animalId", this.animalId)
        .addParameter("locationId", this.locationId)
        .executeUpdate()
        .getKey();
    }
  }
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static Sighting find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id";
      Sighting sighting = cn.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    }
  }

  public static List<Sighting> all() {
    List<Sighting> sightings = new ArrayList<Sighting>();
    return sightings;
  }



  // getters and setters for each property

}
