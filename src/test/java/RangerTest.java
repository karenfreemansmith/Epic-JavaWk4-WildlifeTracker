import org.junit.*;
import static org.junit.Assert.*;

public class RangerTest {
  private Ranger ranger1;
  private Ranger ranger2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    ranger1 = new Ranger("Mike", "Jones", "321-456-2341", "1234 State St", "Bolder", "MT", "65432", "mike@jones.com", 2345, "ranger.jones@park.com");
    ranger2 = new Ranger("Joe", "Smith", "321-456-9876", "1234 Center St", "Bolder", "MT", "65432", "joe@smith.com", 2345, "ranger.smith@park.com");
  }

  @Test
  public void Range_instantiatesCorrectly_true() {
    assertTrue(ranger1 instanceof Ranger);
  }

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(ranger1.getId()>0);
  }

  @Test
  public void delete_deletesRanger_true() {
    int rangerId = ranger2.getId();
    ranger2.delete();
    assertEquals(null, Ranger.find(rangerId));
  }

  @Test
  public void find_returnCorrectRanger_true() {
    assertTrue(Ranger.find(ranger1.getId()).equals(ranger1));
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
