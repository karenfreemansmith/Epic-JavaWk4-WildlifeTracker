import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Location  implements DatabaseManagement {
  private int id;
  private String description;
  private int maprow;
  private int mapcol;

  public Location(String description, int maprow, int mapcol) {
    this.description = description;
    this.maprow = maprow;
    this.mapcol = mapcol;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO locations (description, maprow, mapcol) VALUES (:description, :maprow, :mapcol)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("description", this.description)
        .addParameter("maprow", this.maprow)
        .addParameter("mapcol", this.mapcol)
        .executeUpdate()
        .getKey();
    }
  }

  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static Location find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations WHERE id=:id";
      Location location = cn.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Location.class);
      return location;
    }
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
