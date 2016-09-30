import org.junit.*;
import static org.junit.Assert.*;

public class TrainerTest {
  private Trainer trainer1;
  private Trainer trainer2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    trainer1 = new Trainer("Joe", "Smith", "321-456-9876", "1234 Center St", "Bolder", "MT", "65432", "joe@smith.com", "YogiBear", 14);
    trainer2 = new Trainer("Sue", "Smith", "321-456-1234", "1234 Center St", "Bolder", "MT", "65432", "sue@smith.com", "PicinicBasket", 20);
  }

  @Test
  public void Trainer_instantiatesCorrectly_true() {
    assertTrue(trainer1 instanceof Trainer);
  }
}
