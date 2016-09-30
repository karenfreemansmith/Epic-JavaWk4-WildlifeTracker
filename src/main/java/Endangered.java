import org.sql2o.*;
import java.util.List;

public class Endangered extends Animal {
  private int age;
  private int health;

  public Endangered(String name, String photo, int type, int age, int health) {
    super(name, photo, type);
    this.age = age;
    this.health = health;
  }

  public static Endangered find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      Endangered animal = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Endangered.class);
      return animal;
    }
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age=age;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE animals SET age = :age WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health=health;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE animals SET health = :health WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("health", health)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
