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

  @Override
  public void delete() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM people WHERE id = :id;";
      cn.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherPerson) {
    if(!(otherPerson instanceof Person)) {
      return false;
    } else {
      Person newPerson = (Person) otherPerson;
      return this.getId()==newPerson.getId();
    }
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
  public int getId() {
    return this.id;
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
