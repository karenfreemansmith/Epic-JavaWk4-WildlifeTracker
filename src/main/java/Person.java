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

  public static final int RANGER_TYPE = 1;
  public static final int VISITOR_TYPE = 2;
  public static final int TRAINER_TYPE = 3;

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

  public int getId() {
    return this.id;
  }

  public String getFirstName() {
    return firstname;
  }

  public void setFirstName(String name) {
    this.firstname=name;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET firstname = :firstname WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("firstname", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getLastName() {
    return lastname;
  }

  public void setLastName(String name) {
    this.lastname=name;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET lastname = :lastname WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("lastname", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getPhoneNumber() {
    return phonenumber;
  }

  public void setPhoneNumber(String phone) {
    this.phonenumber=phone;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET phonenumber = :phonenumber WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("phonenumber", phone)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address=address;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET address = :address WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("address", address)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city=city;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET city = :city WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("city", city)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state=state;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET state = :state WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("state", state)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip=zip;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET zip = :zip WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("zip", zip)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email=email;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE people SET email = :email WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("email", email)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
