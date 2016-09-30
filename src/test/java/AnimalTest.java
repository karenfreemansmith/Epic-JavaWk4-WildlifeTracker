import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {
  private Animal animal1;
  private Animal animal2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    animal1 = new Animal();
    animal2 = new Animal();
  }
}
