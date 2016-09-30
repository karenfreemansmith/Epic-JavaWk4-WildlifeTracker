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

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(animal1.getId()>0);
  }

  @Test
  public void delete_deletesAnimal_true() {
    int animalId = animal2.getId();
    animal2.delete();
    assertEquals(null, Animal.find(animalId));
  }

  @Test
  public void find_returnCorrectEndangered_true() {
    assertTrue(Animal.find(animal1.getId()).equals(animal1));
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
}
