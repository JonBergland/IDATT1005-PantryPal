package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import stud.ntnu.idatt1005.pantrypal.controllers.Controller;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

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
  public NavBar(Controller controller) {
    // Create buttons
    Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
    NavLink homeButton = createButton("Home", () -> controller.onNavLinkPress(Route.HOME));
    NavLink pantryButton = createButton("Pantry", null);
    NavLink shoppingListButton = createButton("Shopping List", null);
    NavLink cookbookButton = createButton("Cookbook", () -> controller.onNavLinkPress(Route.COOKBOOK));
    NavLink loginButton = createButton("Login",()-> controller.onNavLinkPress(Route.LOGIN));

    // Create an HBox for the first four buttons
    HBox navigationButtonsBox = new HBox(
        homeButton, pantryButton, shoppingListButton, cookbookButton);
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
    navBar.setStyle("-fx-background-color: #FFFFFF; -fx-border-color:#000000; -fx-border-width: 1 0 1 0;");
    navBar.setPrefWidth(primaryScreenBounds.getWidth()-2);
  }

  /**
   * Creates a styled button with the specified text and action.
   *
   * @param text   Text for the button.
   * @param action Action to be executed on button click.
   * @return Styled Button.
   */
  private NavLink createButton(String text, Runnable action) {
    NavLink link = new NavLink(text);
    link.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill:black;"
        + "-fx-font-family: 'Arial'; -fx-font-size: 14;");
    if (action != null) {
      link.setOnAction(event -> action.run());
    }
    return link;
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
