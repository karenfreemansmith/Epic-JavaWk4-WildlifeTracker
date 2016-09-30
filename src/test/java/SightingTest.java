import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {
  private Sighting sighting1;
  private Sighting sighting2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    sighting1 = new Sighting(1,2,2);
    sighting2 = new Sighting(2,1,1);
  }

  @Test
  public void Sighting_instantiatesCorrectly_true() {
    assertTrue(sighting1 instanceof Sighting);
  }

  // @Test
  // public void save_returnsIdFromDatabase_true() {
  //   assertTrue(sighting1.getId()>0);
  // }
  //
  // @Test
  // public void delete_deletesASighting_true() {
  //   int sightingId = sighting2.getId();
  //   sighting2.delete();
  //   assertEquals(null, Sighting.find(sightingId));
  // }
  //
  // @Test
  // public void find_returnCorrectSighting_true() {
  //   assertTrue(Sighting.find(sighting1.getId()).equals(sighting1));
  // }

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
