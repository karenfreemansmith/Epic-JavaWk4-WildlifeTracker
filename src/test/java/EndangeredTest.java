import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest {
  private Endangered endangered1;
  private Endangered endangered2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    endangered1 = new Endangered("spotted owl", "image.jpg", 2, 1, 1);
    endangered2 = new Endangered("Canada Lynx", "image.jpg", 2, 2, 3);
  }

  @Test
  public void Endangered_instantiatesCorrectly_true() {
    assertTrue(endangered1 instanceof Endangered);
  }
}
