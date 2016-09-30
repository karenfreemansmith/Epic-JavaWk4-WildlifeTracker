import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Person  implements DatabaseManagement {
  public int id;
  public String lastname;
  public String firstname;
  public String phonenumber;
  public String address;
  public String city;
  public String state;
  public String zip;
  public String email;

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO people (firstname, lastname, phonenumber, address, city, state, zip, email) VALUES (:firstname, :lastname, :phonenumber, :address, :city, :state, :zip, :email)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("lastname", this.lastname)
        .addParameter("firstname", this.firstname)
        .addParameter("phonenumber", this.phonenumber)
        .addParameter("address", this.address)
        .addParameter("city", this.city)
        .addParameter("state", this.state)
        .addParameter("zip", this.zip)
        .addParameter("email", this.email)
        .executeUpdate()
        .getKey();
    }
  }
  public void delete() {}

  @Override
  public boolean equals(Object otherObject) {
    return false;
  }

  public static List<Person> all() {
    List<Person> people = new ArrayList<Person>();
    return people;
  }

  public static List<Sighting> allByPerson(int id) {
    List<Sighting> sightings = new ArrayList<Sighting>();
    return sightings;
  }


  // getters and setters for each property

}
