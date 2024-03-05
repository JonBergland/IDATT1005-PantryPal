package stud.ntnu.idatt1005.pantrypal.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import stud.ntnu.idatt1005.pantrypal.views.components.NavBar;

public class HomeView extends Scene {
  private final Button addGroceryButton;
  private final Button addRecipeButton;

  public HomeView(double width, double height) {
    super(new BorderPane(), width, height);

    BorderPane root = (BorderPane) getRoot();

    NavBar navBar = new NavBar();
    root.setTop(navBar.getNavBar());


    addGroceryButton = new Button("Add Grocery");
    addRecipeButton = new Button("Add Recipe");


    VBox buttonContainer = new VBox(10);
    buttonContainer.getChildren().addAll(
        addGroceryButton,
        addRecipeButton

    );
    root.setLeft(buttonContainer);
  }

  public Button getAddGroceryButton() {
    return addGroceryButton;
  }

  public Button getAddRecipeButton() {
    return addRecipeButton;
  }


  public static HomeView create(Stage stage) {
    HomeView homeView = new HomeView(800, 600);
    stage.setScene(homeView);
    stage.show();
    return homeView;
  }
}
