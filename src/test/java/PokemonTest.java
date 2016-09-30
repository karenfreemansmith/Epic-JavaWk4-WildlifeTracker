import org.junit.*;
import static org.junit.Assert.*;

public class PokemonTest {}
  private Pokemon pokemon1;
  private Pokemon pokemon2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    pokemon1 = new Pokemon();
    pokemon2 = new Pokemon();
  }
