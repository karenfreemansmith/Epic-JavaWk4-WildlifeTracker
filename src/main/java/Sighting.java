import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

public class Sighting  implements DatabaseManagement {
  // object properties

  public Sighting() {}

  public void save() {}
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static Sighting find(int id) {
    Sighting sighting = new Sighting();
    return sighting;
  }

  public static List<Sighting> all() {
    List<Sighting> sightings = new ArrayList<Sighting>();
    return sightings;
  }



  // getters and setters for each property

}
