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
}
