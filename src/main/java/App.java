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
      //model.put("sightings", Sighting.all());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      //Sighting sighting = new Sighting(request.queryParams("animal"), request.queryParams("location"));
      //model.put("sighting", sighting);
      //model.put("sightings", Sighting.all());
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
