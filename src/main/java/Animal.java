import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Animal implements DatabaseManagement {
  public int id;
  public String animalname;
  public String photo;
  public int type;

  public Animal(String name, String photo, int type) {
    this.animalname = name;
    this.photo = photo;
    this.type = type;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (animalname, photo, type) VALUES (:name, :photo, :type)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("name", this.animalname)
        .addParameter("photo", this.photo)
        .addParameter("type", this.type)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public void delete() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id = :id;";
      cn.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getId()==newAnimal.getId();
    }
  }

  public static Animal find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      Animal animal = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  public static List<Animal> all() {
    List<Animal> animals = new ArrayList<Animal>();
    return animals;
  }
  public List<Sighting> allByAnimal() {
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

}
