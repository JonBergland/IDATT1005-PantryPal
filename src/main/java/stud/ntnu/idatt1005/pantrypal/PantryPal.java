package stud.ntnu.idatt1005.pantrypal;

import javafx.application.Application;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.controllers.SceneManager;
import stud.ntnu.idatt1005.pantrypal.views.HomeView;

public class PantryPal extends Application {

  @Override
  public void start(Stage primaryStage) {
    SceneManager.setPrimaryStage(primaryStage);
    SceneManager.setScene(new HomeView());
    primaryStage.setTitle("PantryPal");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
