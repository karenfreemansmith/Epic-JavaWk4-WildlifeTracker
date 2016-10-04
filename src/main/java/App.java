import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") != null) {
       port = Integer.parseInt(process.environment().get("PORT"));
    } else {
       port = 4567;
    }

    setPort(port);

    Animal.initializeData();

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("rangers", Ranger.all());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /**************************/
    /*Location-Related Routes */
    /**************************/

    get("/locations/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("location", Location.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByLocation(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/location.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin/addLocation", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location location = new Location(request.queryParams("description"),Integer.parseInt(request.queryParams("mapcol")),Integer.parseInt(request.queryParams("maprow")));
      model.put("newLocation", location);
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("rangers", Ranger.all());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/locations/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("location", Location.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByLocation(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/location-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin/locations/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location location = Location.find(Integer.parseInt(request.params(":id")));
      location.setDescription(request.queryParams("description"));
      location.setMapRow(Integer.parseInt(request.queryParams("maprow")));
      location.setMapCol(Integer.parseInt(request.queryParams("mapcol")));
      model.put("location", Location.find(location.getId()));
      model.put("sightings", Sighting.allByLocation(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/location-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/delete/locations/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location.find(Integer.parseInt(request.params(":id"))).delete();
      response.redirect("/admin");
      model.put("template", "templates/location.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /**************************/
    /* People-Related Routes  */
    /**************************/

    post("/admin/addRanger", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger ranger = new Ranger(request.queryParams("first"), request.queryParams("last"),request.queryParams("phone"),request.queryParams("address"),request.queryParams("city"), request.queryParams("state"),request.queryParams("zip"),request.queryParams("email"),Integer.parseInt(request.queryParams("badge")), request.queryParams("contact"));
      model.put("newRanger", ranger);
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("rangers", Ranger.all());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/rangers/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("ranger", Ranger.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByRanger(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/ranger-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin/rangers/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger person = Ranger.find(Integer.parseInt(request.params(":id")));
      person.setFirstName(request.queryParams("first"));
      person.setLastName(request.queryParams("last"));
      person.setPhoneNumber(request.queryParams("phone"));
      person.setAddress(request.queryParams("address"));
      person.setCity(request.queryParams("city"));
      person.setState(request.queryParams("state"));
      person.setZip(request.queryParams("zip"));
      person.setEmail(request.queryParams("email"));
      person.setBadge(Integer.parseInt(request.queryParams("badge")));
      person.setContact(request.queryParams("contact"));
      model.put("ranger", Ranger.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByRanger(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/ranger-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sign-in/rangers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      response.redirect("/endangered/sightings/" + Ranger.findByBadge(Integer.parseInt(request.queryParams("badge"))).getId());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/profile/rangers/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Ranger person = Ranger.find(Integer.parseInt(request.params(":id")));
      person.setFirstName(request.queryParams("first"));
      person.setLastName(request.queryParams("last"));
      person.setPhoneNumber(request.queryParams("phone"));
      person.setAddress(request.queryParams("address"));
      person.setCity(request.queryParams("city"));
      person.setState(request.queryParams("state"));
      person.setZip(request.queryParams("zip"));
      person.setEmail(request.queryParams("email"));
      response.redirect("/endangered/sightings/" + person.getId());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sign-in/visitors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Visitor visitor;
      try {
        visitor = new Visitor("", "", "", "", "", "", "", request.queryParams("email"));
      } catch (UnsupportedOperationException e) {
        visitor = Visitor.findByEmail(request.queryParams("email"));
        if(e.getMessage()=="Visitor already exists") {
          model.put("msg", "Welcome Back");
        }
      }
      response.redirect("/animals/sightings/" + visitor.getId());
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/profile/visitors/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Visitor person = Visitor.find(Integer.parseInt(request.params(":id")));
      person.setFirstName(request.queryParams("first"));
      person.setLastName(request.queryParams("last"));
      person.setPhoneNumber(request.queryParams("phone"));
      person.setAddress(request.queryParams("address"));
      person.setCity(request.queryParams("city"));
      person.setState(request.queryParams("state"));
      person.setZip(request.queryParams("zip"));
      person.setEmail(request.queryParams("email"));
      response.redirect("/animals/sightings/" + person.getId());
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /**************************/
    /* Animal-Related Routes  */
    /**************************/

    /* Endangered Species  */
    get("/endangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("animals", Animal.allEndangered());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered/id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allEndangered());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered/sightings/:rangerid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("person", Ranger.find(Integer.parseInt(request.params(":rangerid"))));
      model.put("sightings", Sighting.allEndangered());
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("template", "templates/ranger-sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered/sightings/:rangerid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int personId = Integer.parseInt(request.params(":rangerid"));
      int animalId = Integer.parseInt(request.queryParams("animal"));
      int locationId = Integer.parseInt(request.queryParams("location"));
      int age = Integer.parseInt(request.queryParams("age"));
      int health = Integer.parseInt(request.queryParams("health"));
      try {
        Endangered sighting = new Endangered(personId, animalId, locationId, age, health);
      } catch(IllegalArgumentException e) {
        model.put("msg", "Sighting not saved: " + e.getMessage());
      }
      model.put("person", Ranger.find(Integer.parseInt(request.params(":rangerid"))));
      model.put("sightings", Sighting.allEndangered());
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("template", "templates/ranger-sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/endangered/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByAnimal(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/animal-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin/endangered/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByAnimal(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/animal-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Non-Endangered Animals  */
    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("visitor", true);
      model.put("animals", Animal.all());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByAnimal(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/sightings/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("person", Visitor.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allAnimals());
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("template", "templates/visitor-sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/sightings/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int personId = Integer.parseInt(request.params(":id"));
      int animalId = Integer.parseInt(request.queryParams("animal"));
      int locationId = Integer.parseInt(request.queryParams("location"));
      Sighting sighting = new Sighting(personId, animalId, locationId);
      model.put("person", Visitor.find(personId));
      model.put("sightings", Sighting.allAnimals());
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("template", "templates/visitor-sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin/addAnimal", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = new Animal(request.queryParams("animal"), request.queryParams("photo"), Integer.parseInt(request.queryParams("type")));
      model.put("newAnimal", animal);
      model.put("animals", Animal.all());
      model.put("locations", Location.all());
      model.put("rangers", Ranger.all());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByAnimal(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/animal-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/admin/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
      animal.setName(request.queryParams("animal"));
      animal.setPhoto(request.queryParams("photo"));
      animal.setType(Integer.parseInt(request.queryParams("type")));
      model.put("animal", animal);
      model.put("sightings", Sighting.allByAnimal(animal.getId()));
      model.put("template", "templates/animal-admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/delete/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Animal.find(Integer.parseInt(request.params(":id"))).delete();
      response.redirect("/admin");
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /**************************/
    /* Pokemon-Related Routes */
    /**************************/
    get("/pokemon", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("trainer", true);
      model.put("animals", Animal.allPokemon());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/pokemon/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animal", Animal.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allByAnimal(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/pokemon.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sign-in/trainers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Trainer trainer;
      try {
        trainer = new Trainer("", "", "", "", "", "", "", request.queryParams("email"), request.queryParams("nickname"), Integer.parseInt(request.queryParams("level")));
      } catch (UnsupportedOperationException e){
        trainer = Trainer.findByName(request.queryParams("nickname"));
        if(e.getMessage()=="Trainer already exists") {
          model.put("msg", "Welcome Back");
        }
      }
      response.redirect("/pokemon/sightings/"+trainer.getId());
      model.put("template", "templates/error.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/profile/trainers/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Trainer person = Trainer.find(Integer.parseInt(request.params(":id")));
      person.setFirstName(request.queryParams("first"));
      person.setLastName(request.queryParams("last"));
      person.setPhoneNumber(request.queryParams("phone"));
      person.setAddress(request.queryParams("address"));
      person.setCity(request.queryParams("city"));
      person.setState(request.queryParams("state"));
      person.setZip(request.queryParams("zip"));
      person.setEmail(request.queryParams("email"));
      person.setTrainerName(request.queryParams("nickname"));
      person.setLevel(Integer.parseInt(request.queryParams("level")));
      response.redirect("/pokemon/sightings/" + person.getId());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/pokemon/sightings/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("person", Trainer.find(Integer.parseInt(request.params(":id"))));
      model.put("sightings", Sighting.allPokemon());
      model.put("animals", Animal.allPokemon());
      model.put("locations", Location.all());
      model.put("template", "templates/trainer-sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/pokemon/sightings/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int personId = Integer.parseInt(request.params(":id"));
      int animalId = Integer.parseInt(request.queryParams("animal"));
      int locationId = Integer.parseInt(request.queryParams("location"));
      int cp = Integer.parseInt(request.queryParams("cp"));
      Pokemon sighting = new Pokemon(personId, animalId, locationId, cp);
      model.put("person", Trainer.find(personId));
      model.put("sightings", Sighting.allPokemon());
      model.put("animals", Animal.allPokemon());
      model.put("locations", Location.all());
      model.put("template", "templates/trainer-sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
