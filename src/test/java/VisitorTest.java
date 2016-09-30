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

  // @Test
  // public void getNotes_returnsCorrectNotes_String() {
  //   assertEquals("allergies", client.getNotes());
  // }
  //
  // @Test
  // public void setNotes_updatesNotes_String() {
  //   client2.setNotes("allergies");
  //   assertEquals("allergies", Client.find(client2.getId()).getNotes());
  // }
}
