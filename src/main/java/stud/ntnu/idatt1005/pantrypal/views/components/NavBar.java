package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
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
   * Button for navigating to the home page.
   */
  Button homeButton = new Button("Home");
  /**
   * Button for navigating to the pantry page.
   */
  Button pantryButton = new Button("Pantry");
  /**
   * Button for navigating to the shopping list page.
   */
  Button shoppingListButton = new Button("Shopping List");
  /**
   * Button for navigating to the recipes page.
   */
  Button recipesButton = new Button("Recipes");
  /**
   * Button for logging in.
   */
  Button login = new Button("Login");

  /**
   * A separator between the navigation buttons and the login button.
   */
  Rectangle separator = new Rectangle(2, 38);

  /**
   * Spacing between the home button and pantry button.
   */
  Rectangle spacing = new Rectangle(8, 0);
  /**
   * Spacing between the recipes button and the login button.
   */
  Rectangle spacing2 = new Rectangle(200, 0);

  /**
   * Constructor for NavBar.
   * Initializes the buttons, styles, and layout components.
   * On action, the buttons will navigate to the corresponding view.
   */
  public NavBar() {
    homeButton.setOnAction(event -> SceneManager.setScene(new HomeView(800, 600)));
    recipesButton.setOnAction(event -> SceneManager.setScene(new RecipeView(new BorderPane(), 800, 600)));

    homeButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill:black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14;");

    pantryButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14; ");

    shoppingListButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14;");

    recipesButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14;");

    login.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14;");

    navBar.setStyle("-fx-background-color: #FFFFFF;-fx-spacing:30;"
        + "-fx-border-color:#000000;");

    navBar.getItems().addAll(spacing, homeButton,
        pantryButton, shoppingListButton, recipesButton, spacing2, separator, login);
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
