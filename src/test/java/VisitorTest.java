import org.junit.*;
import static org.junit.Assert.*;

public class VisitorTest {
  private Visitor visitor1;
  private Visitor visitor2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    visitor1 = new Visitor("Joe", "Smith", "321-456-9876", "1234 Center St", "Bolder", "MT", "65432", "joe@smith.com");
    visitor2 = new Visitor("Sue", "Smith", "321-456-1234", "1234 Center St", "Bolder", "MT", "65432", "sue@smith.com");
  }

  @Test
  public void visitor_instantiatesCorrectly_true() {
    assertTrue(visitor1 instanceof Visitor);
  }
}
