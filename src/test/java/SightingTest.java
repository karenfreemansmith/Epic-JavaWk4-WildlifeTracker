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
}
