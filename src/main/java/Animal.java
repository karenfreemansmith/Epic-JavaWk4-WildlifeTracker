import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Animal implements DatabaseManagement {
  private int id;
  private String animalname;
  private String photo;
  private int type;

  public Animal(String name, String photo, int type) {
    this.animalname = name;
    this.photo = photo;
    this.type = type;
    this.save();
  }

  public void save() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (animalname, photo, type) VALUES (:name, :photo, :type)";
      this.id = (int) cn.createQuery(sql, true)
        .addParameter("name", this.animalname)
        .addParameter("photo", this.photo)
        .addParameter("type", this.type)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public void delete() {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id = :id;";
      cn.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getId()==newAnimal.getId();
    }
  }

  public static Animal find(int id) {
    try(Connection cn = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      Animal animal = cn.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  public List<Sighting> allByAnimal() {
    String sql = "SELECT * FROM sightings WHERE animalid = :id ORDER BY time";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Sighting.class);
    }
  }

  public static List<Animal> all() {
    String sql;
    // check to see if table is empty and run load routines if it is
    // loadAnimals();
    // loadEndangered();
    // loadPokemon();
    sql = "SELECT * FROM animals ORDER BY animalname";
    try(Connection cn = DB.sql2o.open()) {
      return cn.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Animal.class);
    }
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return animalname;
  }

  public void setName(String name) {
    this.animalname=name;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE animals SET animalname = :name WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("name", animalname)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo=photo;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE animals SET photo = :photo WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("photo", photo)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type=type;
    try(Connection cn = DB.sql2o.open()) {
      String sql = "UPDATE animals SET type = :type WHERE id = :id";
      cn.createQuery(sql)
        .addParameter("type", type)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  // This app should be about recording sightings, not entering animals, so here
  // is a list of some standard animals to start with... list can be customized
  // from the website to add/edit/delete animals as desired

  public static void loadAnimals() {
    Animal newAnimal;
    newAnimal = new Animal("Opossum (Didelphis virginiana)", "006_opossum_nwwd_odfw.jpg", 1);
    newAnimal = new Animal("Mountain Beaver (Aplodontia rufa)", "mountain_beaver.jpg", 1);
    newAnimal = new Animal("American Beaver (Castor canadensis)", "beaver.jpg", 1);
    newAnimal = new Animal("Common Porcupine (Erethizon dorsatum)", "porcupine.jpg", 1);
    newAnimal = new Animal("Nutria (Myocastor Coypus)", "Nutria.jpg", 1);
    newAnimal = new Animal("Black Bear (Ursus americanus)", "black_bear_walking_odfw.jpg", 1);
    newAnimal = new Animal("Cougar (Puma concolor)", "cougar.jpg", 1);
    newAnimal = new Animal("Bobcat (Lynx rufus)", "bobcat_odfw.jpg", 1);
    newAnimal = new Animal("Coyote (Canis latrans)", "coyote_yip.jpg", 1);
    newAnimal = new Animal("Common Gray Fox (Urocyon cinereoargenteus)", "greyfox_odfw.jpg", 1);
    newAnimal = new Animal("Kit Fox (Vulpes velox)", "kit_fox_wikipedia.jpg", 1);
    newAnimal = new Animal("Red Fox (Vulpes vulpes)", "red_fox_odfw.jpg", 1);
  }

  public static void loadEndangered() {
    Animal newEndangered;
    newEndangered = new Animal("Gray Wold (Canis lupus)", "gray_wolf_USFWS.jpg", 2);
    newEndangered = new Animal("Canada Lynx (Lynx canadensis)", "canada_lynx_usfws.jpg", 2);
    newEndangered = new Animal("Columbian White-tailed Deer (Odocoileus virginianus leucurus)", "CWTD-2FWS.jpg", 2);
    newEndangered = new Animal("Marbled Murrelet (Brachyramphus marmoratus)", "MarbledMurreletFWS.jpg", 2);
    newEndangered = new Animal("Western Snowy Plover (Charadrius alexandrinus nivosus)", "WSnowyPloverMikeBairdCC.jpg", 2);
    newEndangered = new Animal("Yellow-billed Cuckoo *Coccyzua americanus)", "YBC-Wikipedia.jpg", 2);
    newEndangered = new Animal("Streaked Horned Lark (Eremophila alphestris stigata)", "STHL-RodGilbert.jpg", 2);
    newEndangered = new Animal("Short-tailed Albatross Phoebastria albatrus)", "Short-tailed-albatross.jpg", 2);
    newEndangered = new Animal("Nothern Spotted Owl (Strix occidentalis caurina)", "northern_spotted_owl_family_USFWS.jpg", 2);
    newEndangered = new Animal("Sea Turtle", "GreenTurtleRPVanDam.jpg", 2);
    newEndangered = new Animal("Oregon Spotted Frog (Rana pretiosa)", "OR.SpottedFrogGaryNafisCalHerps.jpg", 2);
    newEndangered = new Animal("Vernal Pool Fairy Shrimp (Branchinecta lynchi)", "VernalPoolFairyShrimpFWS.jpg", 2);
    newEndangered = new Animal("Taylor's Checkerspot Butterfly (Euphydryas editha taylor)", "TaylorsCheckerspot.FWS.jpg", 2);
    newEndangered = new Animal("Fender's Blue Butterfly (Icaricia icarioides fenderi)", "FendersBlueFWS.jpg", 2);
    newEndangered = new Animal("Oregon Silverspot Butterfly (Speyeria zerene hippolyta)", "OregonSilverspotFWS.jpg", 2);
  }

  public static void loadPokemon() {
    Animal newPokemon;
    newPokemon = new Animal("Bulbasaur", "pokemon/001.png", 3);
    newPokemon = new Animal("Ivysaur", "pokemon/002.png", 3);
    newPokemon = new Animal("Venusaur", "pokemon/003.png", 3);
    newPokemon = new Animal("Charmander", "pokemon/004.png", 3);
    newPokemon = new Animal("Charmeleon", "pokemon/005.png", 3);
    newPokemon = new Animal("Charizard", "pokemon/006.png", 3);
    newPokemon = new Animal("Squirtle", "pokemon/007.png", 3);
    newPokemon = new Animal("Wartortle", "pokemon/008.png", 3);
    newPokemon = new Animal("Blastoise", "pokemon/009.png", 3);
    newPokemon = new Animal("Caterprie", "pokemon/010.png", 3);
    newPokemon = new Animal("Metapod", "pokemon/011.png", 3);
    newPokemon = new Animal("Butterfree", "pokemon/012.png", 3);
    newPokemon = new Animal("Weedle", "pokemon/013.png", 3);
    newPokemon = new Animal("Kakuna", "pokemon/013.png", 3);
    newPokemon = new Animal("Beedrill", "pokemon/015.png", 3);
    newPokemon = new Animal("Pidgey", "pokemon/016.png", 3);
    newPokemon = new Animal("Pigeotto", "pokemon/017.png", 3);
    newPokemon = new Animal("Pidgeot", "pokemon/018.png", 3);
    newPokemon = new Animal("Rattata", "pokemon/019.png", 3);
    newPokemon = new Animal("Raticate", "pokemon/020.png", 3);
    newPokemon = new Animal("Spearow", "pokemon/021.png", 3);
    newPokemon = new Animal("Fearow", "pokemon/022.png", 3);
    newPokemon = new Animal("Ekans", "pokemon/023.png", 3);
    newPokemon = new Animal("Arbok", "pokemon/024.png", 3);
    newPokemon = new Animal("Pikachu", "pokemon/025.png", 3);
    newPokemon = new Animal("Raichu", "pokemon/026.png", 3);
    newPokemon = new Animal("Sandshrew", "pokemon/027.png", 3);
    newPokemon = new Animal("Sandslash", "pokemon/028.png", 3);
    newPokemon = new Animal("Nidoran", "pokemon/029.png", 3);
    newPokemon = new Animal("Nidorina", "pokemon/030.png", 3);
    newPokemon = new Animal("Nidoqueen", "pokemon/031.png", 3);
    newPokemon = new Animal("Nidoran", "pokemon/032.png", 3);
    newPokemon = new Animal("Nidorino", "pokemon/033.png", 3);
    newPokemon = new Animal("Nidoking", "pokemon/034.png", 3);
    newPokemon = new Animal("Clefairy", "pokemon/035.png", 3);
    newPokemon = new Animal("Clefable", "pokemon/036.png", 3);
    newPokemon = new Animal("Vulpix", "pokemon/037.png", 3);
    newPokemon = new Animal("Ninetales", "pokemon/038.png", 3);
    newPokemon = new Animal("Jigglypuff", "pokemon/039.png", 3);
    newPokemon = new Animal("Wigglytuff", "pokemon/040.png", 3);
    newPokemon = new Animal("Zubat", "pokemon/041.png", 3);
    newPokemon = new Animal("Golbat", "pokemon/042.png", 3);
    newPokemon = new Animal("Oddish", "pokemon/043.png", 3);
    newPokemon = new Animal("Gloom", "pokemon/044.png", 3);
    newPokemon = new Animal("Vileplume", "pokemon/045.png", 3);
    newPokemon = new Animal("Paras", "pokemon/046.png", 3);
    newPokemon = new Animal("Parasect", "pokemon/047.png", 3);
    newPokemon = new Animal("Venonat", "pokemon/048.png", 3);
    newPokemon = new Animal("Venomoth", "pokemon/049.png", 3);
    newPokemon = new Animal("Diglett", "pokemon/050.png", 3);
    newPokemon = new Animal("Dugtrio", "pokemon/051.png", 3);
    newPokemon = new Animal("Meowth", "pokemon/052.png", 3);
    newPokemon = new Animal("Persian", "pokemon/053.png", 3);
    newPokemon = new Animal("Psyduck", "pokemon/054.png", 3);
    newPokemon = new Animal("Golduck", "pokemon/055.png", 3);
    newPokemon = new Animal("Mankey", "pokemon/056.png", 3);
    newPokemon = new Animal("Primeape", "pokemon/057.png", 3);
    newPokemon = new Animal("Growlithe", "pokemon/058.png", 3);
    newPokemon = new Animal("Arcanine", "pokemon/059.png", 3);
    newPokemon = new Animal("Poliwag", "pokemon/060.png", 3);
    newPokemon = new Animal("Poliwhirl", "pokemon/61.png", 3);
    newPokemon = new Animal("Poliwrath", "pokemon/062.png", 3);
    newPokemon = new Animal("Abra", "pokemon/063.png", 3);
    newPokemon = new Animal("Kadabra", "pokemon/064.png", 3);
    newPokemon = new Animal("Alakazam", "pokemon/065.png", 3);
    newPokemon = new Animal("Machop", "pokemon/066.png", 3);
    newPokemon = new Animal("Machoke", "pokemon/067.png", 3);
    newPokemon = new Animal("Machamp", "pokemon/068.png", 3);
    newPokemon = new Animal("Bellsprout", "pokemon/069.png", 3);
    newPokemon = new Animal("Weepinbell", "pokemon/070.png", 3);
    newPokemon = new Animal("Victreebel", "pokemon/071.png", 3);
    newPokemon = new Animal("Tentacool", "pokemon/072.png", 3);
    newPokemon = new Animal("Tentacruel", "pokemon/073.png", 3);
    newPokemon = new Animal("Geodude", "pokemon/074.png", 3);
    newPokemon = new Animal("Graveler", "pokemon/075.png", 3);
    newPokemon = new Animal("Golem", "pokemon/076.png", 3);
    newPokemon = new Animal("Ponyta", "pokemon/077.png", 3);
    newPokemon = new Animal("Rapidash", "pokemon/078.png", 3);
    newPokemon = new Animal("Slowpoke", "pokemon/079.png", 3);
    newPokemon = new Animal("Slowbro", "pokemon/080.png", 3);
    newPokemon = new Animal("Magnemite", "pokemon/081.png", 3);
    newPokemon = new Animal("Magneton", "pokemon/082.png", 3);
    newPokemon = new Animal("Farfetch'd", "pokemon/083.png", 3);
    newPokemon = new Animal("Doduo", "pokemon/084.png", 3);
    newPokemon = new Animal("Dodrio", "pokemon/085.png", 3);
    newPokemon = new Animal("Seel", "pokemon/086.png", 3);
    newPokemon = new Animal("Dewgong", "pokemon/087.png", 3);
    newPokemon = new Animal("Grimer", "pokemon/088.png", 3);
    newPokemon = new Animal("Muk", "pokemon/089.png", 3);
    newPokemon = new Animal("Shellder", "pokemon/090.png", 3);
    newPokemon = new Animal("Cloyster", "pokemon/091.png", 3);
    newPokemon = new Animal("Gastly", "pokemon/092.png", 3);
    newPokemon = new Animal("Haunter", "pokemon/093.png", 3);
    newPokemon = new Animal("Gengar", "pokemon/094.png", 3);
    newPokemon = new Animal("Onix", "pokemon/095.png", 3);
    newPokemon = new Animal("Drowzee", "pokemon/096.png", 3);
    newPokemon = new Animal("Hypno", "pokemon/097.png", 3);
    newPokemon = new Animal("Krabby", "pokemon/098.png", 3);
    newPokemon = new Animal("Kingler", "pokemon/099.png", 3);
    newPokemon = new Animal("Voltorb", "pokemon/100.png", 3);
    newPokemon = new Animal("Electrode", "pokemon/101.png", 3);
    newPokemon = new Animal("Exeggcute", "pokemon/102.png", 3);
    newPokemon = new Animal("Exeggutor", "pokemon/103.png", 3);
    newPokemon = new Animal("Cubone", "pokemon/104.png", 3);
    newPokemon = new Animal("Marowak", "pokemon/105.png", 3);
    newPokemon = new Animal("Hitmonlee", "pokemon/106.png", 3);
    newPokemon = new Animal("Hitmonchan", "pokemon/107.png", 3);
    newPokemon = new Animal("Likitung", "pokemon/108.png", 3);
    newPokemon = new Animal("Koffing", "pokemon/109.png", 3);
    newPokemon = new Animal("Weezing", "pokemon/110.png", 3);
    newPokemon = new Animal("Rhyhorn", "pokemon/111.png", 3);
    newPokemon = new Animal("Rhydon", "pokemon/112.png", 3);
    newPokemon = new Animal("Chansey", "pokemon/113.png", 3);
    newPokemon = new Animal("Tangela", "pokemon/114.png", 3);
    newPokemon = new Animal("Kangaskhan", "pokemon/115.png", 3);
    newPokemon = new Animal("Horsea", "pokemon/116.png", 3);
    newPokemon = new Animal("Seadra", "pokemon/117.png", 3);
    newPokemon = new Animal("Goldeen", "pokemon/118.png", 3);
    newPokemon = new Animal("Seaking", "pokemon/119.png", 3);
    newPokemon = new Animal("Staryu", "pokemon/120.png", 3);
    newPokemon = new Animal("Starmie", "pokemon/121.png", 3);
    newPokemon = new Animal("Mr. Mime", "pokemon/122.png", 3);
    newPokemon = new Animal("Syther", "pokemon/123.png", 3);
    newPokemon = new Animal("Jynx", "pokemon/124.png", 3);
    newPokemon = new Animal("Electabuzz", "pokemon/125.png", 3);
    newPokemon = new Animal("Magmar", "pokemon/126.png", 3);
    newPokemon = new Animal("Pinsir", "pokemon/127.png", 3);
    newPokemon = new Animal("Tauros", "pokemon/128.png", 3);
    newPokemon = new Animal("Magikarp", "pokemon/129.png", 3);
    newPokemon = new Animal("Gyarados", "pokemon/130.png", 3);
    newPokemon = new Animal("Lapras", "pokemon/131.png", 3);
    newPokemon = new Animal("Ditto", "pokemon/132.png", 3);
    newPokemon = new Animal("Eevee", "pokemon/133.png", 3);
    newPokemon = new Animal("Vaporeon", "pokemon/134.png", 3);
    newPokemon = new Animal("Jolteon", "pokemon/135.png", 3);
    newPokemon = new Animal("Flareon", "pokemon/136.png", 3);
    newPokemon = new Animal("Porygon", "pokemon/137.png", 3);
    newPokemon = new Animal("Omanyte", "pokemon/138.png", 3);
    newPokemon = new Animal("Omastar", "pokemon/139.png", 3);
    newPokemon = new Animal("Kabuto", "pokemon/140.png", 3);
    newPokemon = new Animal("Kabutops", "pokemon/141.png", 3);
    newPokemon = new Animal("Aerodactyl", "pokemon/142.png", 3);
    newPokemon = new Animal("Snorlax", "pokemon/143.png", 3);
    newPokemon = new Animal("Articuno", "pokemon/144.png", 3);
    newPokemon = new Animal("Zapdos", "pokemon/145.png", 3);
    newPokemon = new Animal("Moltres", "pokemon/146.png", 3);
    newPokemon = new Animal("Dratini", "pokemon/147.png", 3);
    newPokemon = new Animal("Dragonair", "pokemon/148.png", 3);
    newPokemon = new Animal("Dragonite", "pokemon/149.png", 3);
    newPokemon = new Animal("Mewtwo", "pokemon/150.png", 3);
    newPokemon = new Animal("Mew", "pokemon/151.png", 3);
  }

}
