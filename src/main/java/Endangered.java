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
