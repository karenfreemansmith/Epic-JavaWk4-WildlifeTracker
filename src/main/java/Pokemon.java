import org.sql2o.*;
import java.util.List;

public class Pokemon extends Animal {
  private int cp;

  public Pokemon(String name, String photo, int type, int cp) {
    super(name, photo, type);
    this.cp = cp;
  }
}
