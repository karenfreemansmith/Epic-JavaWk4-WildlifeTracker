import org.sql2o.*;
import java.util.List;

public class Trainer extends Person {
  public Trainer() {}

  public static Person find(int id) {
    Person person = new Trainer();
    return person;
  }
}
