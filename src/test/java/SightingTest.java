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

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(sighting1.getId()>0);
  }

  @Test
  public void all_returnsAllInstances_true() {
    assertTrue(Sighting.all().size()>1);
  }

  @Test
  public void all_returnsAllByLocationInstances_true() {
    assertTrue(Sighting.all().size()==1);
  }

  @Test
  public void all_returnsAllByAnimalInstances_true() {
    assertTrue(Sighting.all().size()==1);
  }

  @Test
  public void all_returnsAllByRangerInstances_true() {
    assertTrue(Sighting.all().size()==1);
  }

  @Test
  public void getAnimalId_returnsCorrectAnimal_1() {
    assertEquals(2, sighting1.getAnimalId());
  }

  @Test
  public void getLocationId_returnsCorrectLocation_2() {
    assertEquals(2, sighting1.getLocationId());
  }

  @Test
  public void getPersonId_returnsCorrectPerson_2() {
    assertEquals(1, sighting1.getPersonId());
  }
}
