package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import stud.ntnu.idatt1005.pantrypal.views.components.NavBar;

public class RecipeView extends Scene {

  public RecipeView(BorderPane root, double width, double height) {
    super(root, width, height);

    NavBar navBar = new NavBar();
    root.setTop(navBar.getNavBar());


    VBox buttonContainer = new VBox(10);

    root.setLeft(buttonContainer);

  }

  public static void createAndShow(Stage stage) {
    Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
    BorderPane root = new BorderPane();
    RecipeView recipeView = new RecipeView(root, primaryScreenBounds.getWidth(), 600);
    stage.setScene(new Scene(root, primaryScreenBounds.getWidth(), 600));
    stage.show();
  }

}
