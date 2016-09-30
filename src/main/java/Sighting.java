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
    //this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalid, personid, locationid, time) VALUES (:animalId, :personID, :locationId, now())";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("animalid", this.animalId)
        .addParameter("personid", this.personId)
        .addParameter("locationid", this.locationId)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public void delete() {}
    public static void delete(int id) {
      try(Connection cn = DB.sql2o.open()) {
        String sql = "DELETE FROM sightings WHERE id = :id;";
        cn.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      }
    }

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
  public int getId() {
    return this.id;
  }

  // public String getNotes() {
  //   return notes;
  // }
  //
  // public void setNotes(String notes) {
  //   this.notes=notes;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE clients SET notes = :notes WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("notes", notes)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
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
