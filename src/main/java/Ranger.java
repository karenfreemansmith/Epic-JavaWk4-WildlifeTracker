import org.sql2o.*;
import java.util.List;

public class Ranger extends Person {
  private int rangerId;
  private int badge;
  private String workcontact;

  public Ranger(String firstname, String lastname, String phone, String street, String city, String state, String zip, String email, int badge, String contact) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phone;
    this.address = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.email = email;
    this.badge = badge;
    this.workcontact = contact;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO people (firstname, lastname, phonenumber, address, city, state, zip, email, badge, workcontact) VALUES (:firstname, :lastname, :phonenumber, :address, :city, :state, :zip, :email, :badge, :workcontact)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("lastname", this.lastname)
        .addParameter("firstname", this.firstname)
        .addParameter("phonenumber", this.phonenumber)
        .addParameter("address", this.address)
        .addParameter("city", this.city)
        .addParameter("state", this.state)
        .addParameter("zip", this.zip)
        .addParameter("email", this.email)
        .addParameter("badge", this.badge)
        .addParameter("workcontact", this.workcontact)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static Ranger find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM people WHERE id=:id";
      Ranger ranger = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Ranger.class);
      return ranger;
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
