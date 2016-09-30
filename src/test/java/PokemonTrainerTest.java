imports stuff;import org.junit.*;
import static org.junit.Assert.*;

public class PokemonTrainerTest {}
  private PokemonTrainer trainer1;
  private PokemonTrainer trainer2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    trainer1 = new PokemonTrainer();
    trainer2 = new PokemonTrainer();
  }
