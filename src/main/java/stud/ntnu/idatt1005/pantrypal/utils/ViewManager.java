package stud.ntnu.idatt1005.pantrypal.utils;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class ViewManager {
  private final Stage stage;
  private final Map<Route, Scene> views = new HashMap<>();

  public ViewManager(Stage stage) {
    this.stage = stage;
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