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

  // @Test
  // public void allByLocation_returnsAllSightingsForLocation_true() {
  //   assertTrue(location1.allByLocation().size()>1);
  // }

  @Test
  public void all_returnsAllInstances_true() {
    assertTrue(Location.all().size()>1);
  }

  @Test
  public void find_returnCorrectLocation_true() {
    assertTrue(Location.find(location1.getId()).equals(location1));
  }

  @Test
  public void getDescription_returnsCorrectDescription_String() {
    assertEquals("Mt. Hood Village", location1.getDescription());
  }

  @Test
  public void setDescription_updatesDescription_String() {
    location2.setDescription("Mt. Hood Village");
    assertEquals("Mt. Hood Village", Location.find(location2.getId()).getDescription());
  }

  @Test
  public void getMapRow_returnsCorrectMapRow_22() {
    assertEquals(22, location1.getMapRow());
  }

  @Test
  public void setMapRow_updatesMapRow_33() {
    location2.setMapRow(33);
    assertEquals(33, Location.find(location2.getId()).getMapRow());
  }

  @Test
  public void getMapCol_returnsCorrectMapCol_2() {
    assertEquals(2, location1.getMapCol());
  }

  @Test
  public void setMapCol_updatesMapCol_32() {
    location2.setMapCol(32);
    assertEquals(32, Location.find(location2.getId()).getMapCol());
  }
}
