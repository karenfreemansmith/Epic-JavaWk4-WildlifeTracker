import org.junit.*;
import static org.junit.Assert.*;

public class VisitorTest {
  private Visitor visitor1;
  private Visitor visitor2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    visitor1 = new Visitor();
    visitor2 = new Visitor();
  }
}
