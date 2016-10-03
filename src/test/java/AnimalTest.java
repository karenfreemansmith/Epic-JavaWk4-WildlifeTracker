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
  public void all_returnsAllInstances_true() {
    assertTrue(Animal.all().size()>1);
  }

  @Test
  public void getName_returnsCorrectName_String() {
    assertEquals("Yogi Bear", animal1.getName());
  }

  @Test
  public void setName_updatesName_String() {
    animal1.setName("Brown Bear");
    assertEquals("Brown Bear", Animal.find(animal1.getId()).getName());
  }

  @Test
  public void getPhoto_returnsCorrectPhoto_String() {
    assertEquals("image.jpg", animal1.getPhoto());
  }

  @Test
  public void setPhoto_updatesPhoto_String() {
    animal2.setPhoto("image.jpg");
    assertEquals("image.jpg", Animal.find(animal2.getId()).getPhoto());
  }

  @Test
  public void getType_returnsCorrectType_String() {
    assertEquals(2, animal2.getType());
  }

  @Test
  public void setType_updatesNotes_String() {
    animal2.setType(3);
    assertEquals(3, Animal.find(animal2.getId()).getType());
  }
}
