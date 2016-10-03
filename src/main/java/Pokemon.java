import org.sql2o.*;
import java.util.List;

public class Pokemon extends Sighting {
  private int cp;

  public Pokemon(int personId, int animalId, int locationId, int cp) {
    super(personId, animalId, locationId);
    this.cp = cp;
  }

  public int getCP() {
    return cp;
  }
}
