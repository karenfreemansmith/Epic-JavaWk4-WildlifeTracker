import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Animal implements DatabaseManagement {
  // object properties

  public Animal() {}

  public void save() {}
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static Animal find(int id) {
    Animal animal = new Animal();
    return animal;
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
