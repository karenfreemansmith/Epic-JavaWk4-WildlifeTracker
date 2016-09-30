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
    List<Location> locations = new ArrayList<Location>();
    return locations;
  }
  public static List<Sighting> allByLocation() {
    List<Sighting> sightings = new ArrayList<Sighting>();
    return sightings;
  }

  // getters and setters for each property
  public int getId() {
    return this.id;
  }

  //
  // public String getName() {
  //   return name;
  // }
  //
  // public void setName(String name) {
  //   this.name=name;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET name = :name WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("name", name)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public String getPhoto() {
  //   return photo;
  // }
  //
  // public void setPhoto(String photo) {
  //   this.photo=photo;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET photo = :photo WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("photo", photo)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public String getType() {
  //   return type;
  // }
  //
  // public void setType(String type) {
  //   this.type=type;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET type = :type WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("type", type)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

}
