import org.junit.*;
import static org.junit.Assert.*;

public class RangerTest {
  private Ranger ranger1;
  private Ranger ranger2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    ranger1 = new Ranger();
    ranger2 = new Ranger();
  }
}
