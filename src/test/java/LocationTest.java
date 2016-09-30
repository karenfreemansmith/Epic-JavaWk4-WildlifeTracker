import org.junit.*;
import static org.junit.Assert.*;

public class LocationTest {}
  private Location location1;
  private Location location2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    location1 = new Location();
    location2 = new Location();
  }
