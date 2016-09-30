import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DBRule extends ExternalResource {
  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM sightings *;";
      cn.createQuery(sql).executeUpdate();
      sql = "DELETE FROM animals *;";
      cn.createQuery(sql).executeUpdate();
      sql = "DELETE FROM locations *;";
      cn.createQuery(sql).executeUpdate();
      sql = "DELETE FROM people *;";
      cn.createQuery(sql).executeUpdate();
      sql = "DELETE FROM rangers *;";
      cn.createQuery(sql).executeUpdate();
      sql = "DELETE FROM trainers *;";
      cn.createQuery(sql).executeUpdate();
    }
  }

}
