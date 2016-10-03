import org.junit.*;
import static org.junit.Assert.*;

public class PokemonTest {
  private Pokemon sighting1;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    sighting1 = new Pokemon(1,2,2, 456);
  }

  @Test
  public void Pokemon_instantiatesCorrectly_true() {
    assertTrue(sighting1 instanceof Pokemon);
  }

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(sighting1.getId()>0);
  }
}
