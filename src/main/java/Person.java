import org.sql2o.*;
import java.util.List;

public abstract class Person  implements DatabaseManagement {
  // object properties

  public Animal() {}

  public void save() {}
  public void delete() {}

  @Overrides
  public boolean equals(Object otherObject) {}

  public static Animal find(int id) {}

  public static List<Animal> all() {}
  public static List<Animal> allByLocation() {}
  public static List<Animal> allByRanger() {}
  public static List<Animal> allByTrainer() {}

  // getters and setters for each property

}
