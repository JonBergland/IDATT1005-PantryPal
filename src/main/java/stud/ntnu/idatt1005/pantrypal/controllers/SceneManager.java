package stud.ntnu.idatt1005.pantrypal.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
  private static Stage primaryStage;

  public static void setPrimaryStage(Stage stage) {
    primaryStage = stage;
  }

  public static void setScene(Scene scene) {
    primaryStage.setScene(scene);
  }
}

