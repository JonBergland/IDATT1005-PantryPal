package stud.ntnu.idatt1005.pantrypal.utils;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.controllers.LogInController;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class ViewManager {
  private final Stage stage;
  private final Map<Route, Scene> views = new HashMap<>();

  public ViewManager(Stage stage) {
    this.stage = stage;
  }

  public void init() {
    HomeController homeController = new HomeController(this);
    PantryController pantryController = new PantryController(this);
    ShoppingListController shoppingListController = new ShoppingListController(this, pantryController);
    CookBookController cookBookController = new CookBookController(this, shoppingListController, pantryController);
    LogInController logInController = new LogInController(this);

    //Init view
    this.setView(Route.HOME);
  }

  public void addView(Route route, Scene view) {
    views.put(route, view);
  }

  public void removeView(Route route) {
    views.remove(route);
  }

  public void setView(Route route) {
    stage.setScene(views.get(route));
    stage.setTitle("PantryPal - " + route.toString());
  }

  public Route getCurrentView() {
    Scene currentScene = stage.getScene();
    for (Map.Entry<Route, Scene> entry : views.entrySet()) {
      if (entry.getValue().equals(currentScene)) {
        return entry.getKey();
      }
    }
    return null;
  }
}