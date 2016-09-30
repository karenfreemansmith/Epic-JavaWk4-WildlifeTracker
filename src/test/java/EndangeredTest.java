import org.junit.*;
import static org.junit.Assert.*;

public class EndangeredTest {
  private Endangered endangered1;
  private Endangered endangered2;

  @Rule
  public DBRule database = new DBRule();

  @Before
  public void setup() {
    endangered1 = new Endangered("spotted owl", "image.jpg", 2, 1, 1);
    endangered2 = new Endangered("Canada Lynx", "image.jpg", 2, 2, 3);
  }

  @Test
  public void Endangered_instantiatesCorrectly_true() {
    assertTrue(endangered1 instanceof Endangered);
  }

  @Test
  public void save_returnsIdFromDatabase_true() {
    assertTrue(endangered1.getId()>0);
  }

  @Test
  public void delete_deletesEndangered_true() {
    int endangeredId = endangered2.getId();
    endangered2.delete();
    assertEquals(null, Endangered.find(endangeredId));
  }

  @Test
  public void find_returnCorrectEndangered_true() {
    assertTrue(Endangered.find(endangered1.getId()).equals(endangered1));
  }

  @Test
  public void getAge_returnsCorrectAge_String() {
    assertEquals(1, endangered1.getAge());
  }

  @Test
  public void setAge_updatesAge_int() {
    endangered2.setAge(2);
    assertEquals(2, Endangered.find(endangered2.getId()).getAge());
  }

  @Test
  public void getHealth_returnsCorrectHealth_String() {
    assertEquals(1, endangered1.getHealth());
  }

  @Test
  public void setHealth_updatesHealth_int() {
    endangered2.setHealth(1);
    assertEquals(1, Endangered.find(endangered2.getId()).getHealth());
  }
}
