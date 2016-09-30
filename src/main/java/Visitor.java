import org.sql2o.*;
import java.util.List;

public class Visitor extends Person {
  public Visitor() {}

  public static Person find(int id) {
    Person person = new Visitor();
    return person;
  }
}
