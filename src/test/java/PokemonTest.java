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

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(pokemon1.getId()>0);
  }

  @Test
  public void delete_deletesPokemon_true() {
    int pokemonId = pokemon2.getId();
    pokemon2.delete();
    assertEquals(null, Pokemon.find(pokemonId));
  }

  @Test
  public void find_returnCorrectPokemon_true() {
    assertTrue(Pokemon.find(pokemon1.getId()).equals(pokemon1));
  }

  // @Test
  // public void getNotes_returnsCorrectNotes_String() {
  //   assertEquals("allergies", client.getNotes());
  // }
  //
  // @Test
  // public void setNotes_updatesNotes_String() {
  //   client2.setNotes("allergies");
  //   assertEquals("allergies", Client.find(client2.getId()).getNotes());
  // }

  // @Test
  // public void getNotes_returnsCorrectNotes_String() {
  //   assertEquals("allergies", client.getNotes());
  // }
  //
  // @Test
  // public void setNotes_updatesNotes_String() {
  //   client2.setNotes("allergies");
  //   assertEquals("allergies", Client.find(client2.getId()).getNotes());
  // }
}
