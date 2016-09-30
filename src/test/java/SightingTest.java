import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {
  private Sighting sighting1;
  private Sighting sighting2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    sighting1 = new Sighting();
    sighting2 = new Sighting();
  }
}
