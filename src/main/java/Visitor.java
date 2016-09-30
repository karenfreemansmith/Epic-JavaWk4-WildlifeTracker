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
  }

  public static Visitor find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM people WHERE id=:id";
      Visitor visitor = cn.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Visitor.class);
      return visitor;
    }
  }
}
