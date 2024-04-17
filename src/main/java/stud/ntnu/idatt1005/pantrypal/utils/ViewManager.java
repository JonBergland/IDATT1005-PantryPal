package stud.ntnu.idatt1005.pantrypal.utils;

import java.util.EnumMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.controllers.CookbookController;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.controllers.LogInController;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * The ViewManager class is responsible for managing the views in the application.
 * It keeps track of the views and provides methods for adding, removing, and setting views.
 */
public class ViewManager {
  private final Stage stage;
  private final EnumMap<Route, Scene> views = new EnumMap<>(Route.class);

  /**
   * Constructs a new ViewManager with a given stage.
   *
   * @param stage The stage for the application.
   */
  public ViewManager(Stage stage) {
    this.stage = stage;
  }

  /**
   * Initializes the controllers and views for the application.
   */
  public void init() {
    HomeController homeController = new HomeController(this);
    PantryController pantryController = new PantryController(this);
    ShoppingListController shoppingListController =
        new ShoppingListController(this, pantryController);
    CookbookController cookBookController =
        new CookbookController(this, shoppingListController, pantryController);
    LogInController logInController = new LogInController(this);

    //Init view
    this.setView(Route.HOME);
  }

  /**
   * Adds a view to the view manager.
   *
   * @param route The route of the view.
   * @param view  The view to add.
   */
  public void addView(Route route, Scene view) {
    views.put(route, view);
  }

  /**
   * Removes a view from the view manager.
   *
   * @param route The route of the view to remove.
   */
  public void removeView(Route route) {
    views.remove(route);
  }

  /**
   * Sets the view of the application to a given route.
   *
   * @param route The route of the view to set.
   */
  public void setView(Route route) {
    stage.setScene(views.get(route));
    stage.setTitle("PantryPal - " + route.toString());
  }

  /**
   * Gets the current view of the application.
   *
   * @return The current view.
   */
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