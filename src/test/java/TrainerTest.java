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

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(trainer1.getId()>0);
  }

  @Test
  public void delete_deletesTrainer_true() {
    int trainerId = trainer2.getId();
    trainer2.delete();
    assertEquals(null, Trainer.find(trainerId));
  }

  @Test
  public void find_returnCorrectTrainer_true() {
    assertTrue(Trainer.find(trainer1.getId()).equals(trainer1));
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
