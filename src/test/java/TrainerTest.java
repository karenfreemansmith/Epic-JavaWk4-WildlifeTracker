import org.junit.*;
import static org.junit.Assert.*;

public class TrainerTest {
  private Trainer trainer1;
  private Trainer trainer2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    trainer1 = new Trainer();
    trainer2 = new Trainer();
  }
}
