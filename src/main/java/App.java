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

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/location/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sightings", Sighting.allByLocation(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("rangers", Ranger.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangered/id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sightings", Sighting.allEndangered());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sighting sighting = new Sighting(Integer.parseInt(request.queryParams("person")), Integer.parseInt(request.queryParams("animal")), Integer.parseInt(request.queryParams("location")));
      model.put("sighting", sighting);
      model.put("sightings", Sighting.allEndangered());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("visitors", Visitor.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sightings", Sighting.allAnimals());
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sighting sighting = new Sighting(Integer.parseInt(request.queryParams("person")), Integer.parseInt(request.queryParams("animal")), Integer.parseInt(request.queryParams("location")));
      model.put("sighting", sighting);
      model.put("sightings", Sighting.allAnimals());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/pokemon", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("trainers", Trainer.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/pokemon/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sightings", Sighting.allPokemon());
      model.put("template", "templates/pokemon.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/pokemon", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sighting sighting = new Sighting(Integer.parseInt(request.queryParams("person")), Integer.parseInt(request.queryParams("animal")), Integer.parseInt(request.queryParams("location")));
      model.put("sighting", sighting);
      model.put("sightings", Sighting.allPokemon());
      model.put("template", "templates/sightings.vtl");
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
  }
}
