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
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public void delete() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM locations WHERE id = :id;";
      cn.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherLocation) {
    if(!(otherLocation instanceof Location)) {
      return false;
    } else {
      Location newLocation = (Location) otherLocation;
      return this.getId()==newLocation.getId();
    }
  }

  public static Location find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations WHERE id=:id";
      Location location = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Location.class);
      return location;
    }
  }

  public static List<Location> all() {
    String sql = "SELECT * FROM locations ORDER BY description";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Location.class);
    }
  }

  public List<Sighting> allByLocation() {
    String sql = "SELECT * FROM sightings WHERE locationid = :id ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Sighting.class);
    }
  }

  public int getId() {
    return this.id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description=description;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE locations SET description = :description WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getMapRow() {
    return maprow;
  }

  public void setMapRow(int maprow) {
    this.maprow=maprow;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE locations SET maprow = :maprow WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("maprow", maprow)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getMapCol() {
    return mapcol;
  }

  public void setMapCol(int mapcol) {
    this.mapcol=mapcol;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE locations SET mapcol = :mapcol WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("mapcol", mapcol)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
