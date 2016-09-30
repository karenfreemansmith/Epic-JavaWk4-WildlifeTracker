import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Person  implements DatabaseManagement {
  // object properties

  public void save() {}
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static List<Person> all() {
    List<Person> people = new ArrayList<Person>();
    return people;
  }

  public static List<Sighting> allByPerson(int id) {
    List<Sighting> sightings = new ArrayList<Sighting>();
    return sightings;
  }


  // getters and setters for each property

}
