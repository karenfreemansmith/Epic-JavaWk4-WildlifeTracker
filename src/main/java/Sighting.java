import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Sighting implements DatabaseManagement {
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
      String sql = "INSERT INTO sightings (animalId, locationId, personId) VALUES (:animalId, :locationId, :personId)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("locationId", this.locationId)
        .addParameter("personId", this.personId)
        .throwOnMappingFailure(false)
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
    String sql = "SELECT * FROM sightings ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByLocation(int id) {
    String sql = "SELECT * FROM sightings WHERE locationid = :id ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByAnimal(int id) {
    String sql = "SELECT * FROM sightings WHERE animalid = :id ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allByPerson(int id) {
    String sql = "SELECT * FROM sightings WHERE personid = :id ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", id)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Sighting> allAnimals() {
    String sql = "SELECT * FROM sightings JOIN animals ON animalid = animals.id WHRE animal.type = 1 ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }


  public static List<Sighting> allPokemon() {
    String sql = "SELECT * FROM sightings JOIN animals ON animalid = animals.id WHRE animal.type = 3 ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Sighting.class);
    }
  }


  public static List<Sighting> allEndangered() {
    String sql = "SELECT * FROM sightings JOIN animals ON animalid = animals.id WHRE animal.type = 2 ORDER BY time";
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

  public int getAnimal() {
    return this.animalId;
  }
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
  public int getLocation() {
    return this.locationId;
  }
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
  public int getPerson() {
    return this.personId;
  }
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
