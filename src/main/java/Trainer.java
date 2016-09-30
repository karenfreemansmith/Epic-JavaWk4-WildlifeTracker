import org.sql2o.*;
import java.util.List;

public class Trainer extends Person {
  private int trainerId;
  private String trainerName;
  private int level;

  public Trainer(String firstname, String lastname, String phone, String street, String city, String state, String zip, String email, String nickname, int level) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phone;
    this.address = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.email = email;
    this.trainerName = nickname;
    this.level = level;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO people (firstname, lastname, phonenumber, address, city, state, zip, email, trainername, level) VALUES (:firstname, :lastname, :phonenumber, :address, :city, :state, :zip, :email, :trainername, :level)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("lastname", this.lastname)
        .addParameter("firstname", this.firstname)
        .addParameter("phonenumber", this.phonenumber)
        .addParameter("address", this.address)
        .addParameter("city", this.city)
        .addParameter("state", this.state)
        .addParameter("zip", this.zip)
        .addParameter("email", this.email)
        .addParameter("trainername", this.trainerName)
        .addParameter("level", this.level)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();

    }
  }

  public static Trainer find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM people WHERE id=:id";
      Trainer trainer = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Trainer.class);
      return trainer;
    }
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
