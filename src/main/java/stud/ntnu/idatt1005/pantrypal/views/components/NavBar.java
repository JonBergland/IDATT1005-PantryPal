package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import stud.ntnu.idatt1005.pantrypal.controllers.SceneManager;
import stud.ntnu.idatt1005.pantrypal.views.HomeView;
import stud.ntnu.idatt1005.pantrypal.views.RecipeView;

/**
 * A class for the navigation bar.
 * Goal: To be able to switch between different views in the application fast and easy.
 */
public class NavBar extends Control {
  /**
   * The main toolbar that holds navigation buttons.
   */
  ToolBar navBar = new ToolBar();

  /**
   * A separator between the navigation buttons and the login button.
   */
  Rectangle separator = new Rectangle(2, 38);

  /**
   * Constructor for NavBar.
   * Initializes the buttons, styles, and layout components.
   * On action, the buttons will navigate to the corresponding view.
   */
  public NavBar() {
    // Create buttons
    Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
    Button homeButton = createButton("Home", () -> SceneManager.setScene(
        new HomeView(primaryScreenBounds.getWidth(), 600)));
    Button pantryButton = createButton("Pantry", null);
    Button shoppingListButton = createButton("Shopping List", null);
    Button recipesButton = createButton("Recipes", () -> SceneManager.setScene(new RecipeView(
        new BorderPane(), primaryScreenBounds.getWidth(), 600)));
    Button loginButton = createButton("Login", null);

    // Create an HBox for the first four buttons
    HBox navigationButtonsBox = new HBox(
        homeButton, pantryButton, shoppingListButton, recipesButton);
    navigationButtonsBox.setStyle("-fx-spacing: 40; -fx-padding: 5 10 5 10;");

    // Create an HBox for the login button
    HBox loginButtonBox = new HBox(loginButton);
    loginButtonBox.setStyle("-fx-spacing: 10; -fx-padding: 5 10 5 10;");

    // Use Region to make spacing between the two HBox containers flexible
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    // Add components to the main toolbar
    navBar.getItems().addAll(navigationButtonsBox, spacer, separator, loginButtonBox);

    // Set styles and preferences
    navBar.setStyle("-fx-background-color: #FFFFFF; -fx-border-color:#000000;");
    navBar.setPrefWidth(primaryScreenBounds.getWidth());
  }

  /**
   * Creates a styled button with the specified text and action.
   *
   * @param text   Text for the button.
   * @param action Action to be executed on button click.
   * @return Styled Button.
   */
  private Button createButton(String text, Runnable action) {
    Button button = new Button(text);
    button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill:black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14;");
    if (action != null) {
      button.setOnAction(event -> action.run());
    }
    return button;
  }

  /**
   * Gets the main navigation bar instance.
   *
   * @return The ToolBar representing the navigation bar.
   */
  public ToolBar getNavBar() {
    return navBar;
  }
}
