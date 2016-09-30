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
}
