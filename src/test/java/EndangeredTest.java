import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest {}
  private Endangered endangered1;
  private Endangered endangered2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    endangered1 = new Endangered();
    endangered2 = new Endangered();
  }
