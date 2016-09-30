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
}
