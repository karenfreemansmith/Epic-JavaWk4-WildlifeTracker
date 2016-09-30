import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {
  private Animal animal1;
  private Animal animal2;
  private Animal animal3;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    animal1 = new Animal("Yogi Bear", "image.jpg", 1);
    animal2 = new Animal("Spotted Owl", "", 2);
    animal3 = new Animal("Pikachu", "", 3);
  }

  @Test
  public void Animal_instantiatesCorrectly_true() {
    assertTrue(animal1 instanceof Animal);
  }
}
