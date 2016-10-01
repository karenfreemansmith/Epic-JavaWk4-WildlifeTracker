import org.sql2o.*;
import java.util.List;

public class Pokemon extends Sighting {
  private int cp;

  public Pokemon(int personId, int animalId, int locationId, int cp) {
    super(personId, animalId, locationId);
    this.cp = cp;
  }

  public static Pokemon find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      Pokemon animal = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Pokemon.class);
      return animal;
    }
  }

  public int getCP() {
    return cp;
  }

  public void setCP(int cp) {
    this.cp=cp;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE animals SET cp = :cp WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("cp", cp)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
