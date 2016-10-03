import org.sql2o.*;
import java.util.List;

public class Visitor extends Person {
  public Visitor(String firstname, String lastname, String phone, String street, String city, String state, String zip, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phonenumber = phone;
    this.address = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.email = email;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      //test to see if email is in database already
      String sql = "INSERT INTO people (firstname, lastname, phonenumber, address, city, state, zip, email, type) VALUES (:firstname, :lastname, :phonenumber, :address, :city, :state, :zip, :email, 2)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("lastname", this.lastname)
        .addParameter("firstname", this.firstname)
        .addParameter("phonenumber", this.phonenumber)
        .addParameter("address", this.address)
        .addParameter("city", this.city)
        .addParameter("state", this.state)
        .addParameter("zip", this.zip)
        .addParameter("email", this.email)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Ranger> all() {
    String sql = "SELECT * FROM people WHERE type=2 ORDER BY lastname, firstname";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Ranger.class);
    }
  }

  public static Visitor find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM people WHERE id=:id";
      Visitor visitor = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Visitor.class);
      return visitor;
    }
  }

}
