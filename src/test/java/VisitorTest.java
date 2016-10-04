import org.junit.*;
import static org.junit.Assert.*;

public class VisitorTest {
  private Visitor visitor1;
  private Visitor visitor2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    visitor1 = new Visitor("Joe", "Smith", "321-456-9876", "1234 Center St", "Bolder", "MT", "65432", "joe@smith.com");
    visitor2 = new Visitor("Sue", "Smith", "321-456-1234", "1234 Center St", "Bolder", "MT", "65432", "sue@smith.com");
  }

  @Test
  public void visitor_instantiatesCorrectly_true() {
    assertTrue(visitor1 instanceof Visitor);
  }

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(visitor1.getId()>0);
  }

  @Test
  public void delete_deletesVistitor_true() {
    int visitorId = visitor2.getId();
    visitor2.delete();
    assertEquals(null, Visitor.find(visitorId));
  }

  @Test
  public void find_returnCorrectVisitor_true() {
    assertTrue(Visitor.find(visitor1.getId()).equals(visitor1));
  }

  @Test
  public void findByEmail_returnCorrectVisitor_true() {
    assertTrue(Visitor.findByEmail(visitor1.getEmail()).equals(visitor1));
  }

  @Test
  public void getFirstName_returnsCorrectName_String() {
    assertEquals("Joe", visitor1.getFirstName());
  }

  @Test
  public void setFristName_updatesName_String() {
    visitor2.setFirstName("Joe");
    assertEquals("Joe", Visitor.find(visitor2.getId()).getFirstName());
  }

  @Test
  public void getLastName_returnsCorrectName_String() {
    assertEquals("Smith", visitor1.getLastName());
  }

  @Test
  public void setLastName_updatesName_String() {
    visitor2.setFirstName("Freeman");
    assertEquals("Freeman", Visitor.find(visitor2.getId()).getFirstName());
  }

  @Test
  public void getPhoneNumber_returnsCorrectPhoneNumber_String() {
    assertEquals("321-456-9876", visitor1.getPhoneNumber());
  }

  @Test
  public void setPhoneNumber_updatesPhoneNumber_String() {
    visitor2.setPhoneNumber("503-331-2346");
    assertEquals("503-331-2346", Visitor.find(visitor2.getId()).getPhoneNumber());
  }

  @Test
  public void getAddress_returnsCorrectAddress_String() {
    assertEquals("1234 Center St", visitor1.getAddress());
  }

  @Test
  public void setAddress_updatesAddress_String() {
    visitor2.setAddress("1710 Henderson Ave");
    assertEquals("1710 Henderson Ave", Visitor.find(visitor2.getId()).getAddress());
  }


  @Test
  public void getCity_returnsCorrectCity_String() {
    assertEquals("Bolder", visitor1.getCity());
  }

  @Test
  public void setCity_updatesCity_String() {
    visitor2.setCity("Eugene");
    assertEquals("Eugene", Visitor.find(visitor2.getId()).getCity());
  }

  @Test
  public void getState_returnsCorrectState_String() {
    assertEquals("MT", visitor1.getState());
  }

  @Test
  public void setState_updatesState_String() {
    visitor2.setState("CA");
    assertEquals("CA", Visitor.find(visitor2.getId()).getState());
  }

  @Test
  public void getZip_returnsCorrectZip_int() {
    assertEquals("65432", visitor1.getZip());
  }

  @Test
  public void setZip_updatesZip_int() {
    visitor2.setZip("97403");
    assertEquals("97403", Visitor.find(visitor2.getId()).getZip());
  }

  @Test
  public void getEmail_returnsCorrectEmail_String() {
    assertEquals("joe@smith.com", visitor1.getEmail());
  }

  @Test
  public void setEmail_updatesEmail_String() {
    visitor2.setEmail("karen@sample.com");
    assertEquals("karen@sample.com", Visitor.find(visitor2.getId()).getEmail());
  }

}
