import org.junit.*;
import static org.junit.Assert.*;

public class PokemonTest {
  private Pokemon pokemon1;
  private Pokemon pokemon2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    pokemon1 = new Pokemon("Pikachu", "", 3, 456);
    pokemon2 = new Pokemon("Zapados", "", 3, 234);
  }

  @Test
  public void Pokemon_instantiatesCorrectly_true() {
    assertTrue(pokemon1 instanceof Pokemon);
  }
}
