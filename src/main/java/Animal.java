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
        .executeUpdate()
        .getKey();
    }
  }
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static Animal find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      Animal animal = cn.createQuery(sql)
        .addParameter("id", id)
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

}
