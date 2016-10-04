import org.sql2o.*;
import java.util.List;

public class Pokemon extends Sighting {
  private int cp;

  public Pokemon(int personId, int animalId, int locationId, int cp) {
    super(personId, animalId, locationId);
    this.cp = cp;
    this.save();
  }

  @Override
  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animalId, locationId, personId, time, cp) VALUES (:animalId, :locationId, :personId, now(), :cp)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("animalId", this.animalId)
        .addParameter("locationId", this.locationId)
        .addParameter("personId", this.personId)
        .addParameter("cp", this.cp)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public int getCP() {
    return cp;
  }
}
