import org.sql2o.*;
import java.util.List;

public class Pokemon extends Animal {
  private int cp;

  public Pokemon(String name, String photo, int type, int cp) {
    super(name, photo, type);
    this.cp = cp;
  }

  // public String getNotes() {
  //   return notes;
  // }
  //
  // public void setNotes(String notes) {
  //   this.notes=notes;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE clients SET notes = :notes WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("notes", notes)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
}
