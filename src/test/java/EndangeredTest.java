import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest {
  private Endangered sighting1;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    sighting1 = new Endangered(1,2,2, 2, 2);
  }

  @Test
  public void Endangered_instantiatesCorrectly_true() {
    assertTrue(sighting1 instanceof Endangered);
  }

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(sighting1.getId()>0);
  }
}
