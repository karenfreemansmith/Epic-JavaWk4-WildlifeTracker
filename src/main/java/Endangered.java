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

  //
  // public String getName() {
  //   return name;
  // }
  //
  // public void setName(String name) {
  //   this.name=name;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET name = :name WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("name", name)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  //
  // public String getPhoto() {
  //   return photo;
  // }
  //
  // public void setPhoto(String photo) {
  //   this.photo=photo;
  //   try(Connection cn = DB.sql2o.open()) {
  //     String sql = "UPDATE animals SET photo = :photo WHERE id = :id";
  //     cn.createQuery(sql)
  //       .addParameter("photo", photo)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }

}
