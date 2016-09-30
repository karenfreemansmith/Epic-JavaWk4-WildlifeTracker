import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Location  implements DatabaseManagement {
  // object properties

  public Location() {}

  public void save() {}
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static Location find(int id) {
    Location location = new Location();
    return location;
  }

  public static List<Location> all() {
    List<Location> locations = new ArrayList<Location>();
    return locations;
  }
  public static List<Sighting> allByLocation() {
    List<Sighting> sightings = new ArrayList<Sighting>();
    return sightings;
  }

  // getters and setters for each property

}
