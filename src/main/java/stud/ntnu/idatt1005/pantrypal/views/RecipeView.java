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

}
