import org.junit.*;
import static org.junit.Assert.*;

public class LocationTest {
  private Location location1;
  private Location location2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    location1 = new Location("Mt. Hood Village", 22,2);
    location2 = new Location("Government Camp", 18, 20);
  }

  @Test
  public void Location_instantiatesCorrectly_true() {
    assertTrue(location1 instanceof Location);
  }

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(location1.getId()>0);
  }

  @Test
  public void delete_deletesLocation_true() {
    int locationId = location2.getId();
    location2.delete();
    assertEquals(null, Location.find(locationId));
  }

  @Test
  public void find_returnCorrectLocation_true() {
    assertTrue(Location.find(location1.getId()).equals(location1));
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
