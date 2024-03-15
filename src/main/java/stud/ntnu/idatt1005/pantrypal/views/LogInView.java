package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.LogInController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledTextField;

/**
 * This class represents the LogInView in the application.
 * It extends the View class and sets the scene for the stage.
 * The LogInView includes a title, username input field and a login button.
 * The class is associated with a LogInController to handle the logic
 * and actions related to logging in.
 */
public class LogInView extends View {

  // The controller responsible for managing the logic and actions associated with user login.
  private final LogInController controller;

  // Text representing the login title
  Text loginText = new Text("Login");
  // Username input field
  StyledTextField usernameField = new StyledTextField("Username");
  // Login button
  StyledButton loginButton = new StyledButton(
      "Login", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

  /**
   * Constructor for LogInView.
   *
   * @param controller The LogInController associated with this view.
   */
  public LogInView(LogInController controller) {
    super(controller, Route.LOGIN, "/styles/login.css");
    this.controller = controller;
    initialize();
  }

  /**
   * Initializes the LogInView by setting up the layout and styling.
   */
  public void initialize() {
    // Creates a VBox to contain the login elements
    VBox loginContainer = new VBox(10);
    loginContainer.setAlignment(Pos.CENTER);

    // Add the usernameField and loginButton directly to the VBox
    loginContainer.getChildren().addAll(loginText, usernameField, loginButton);
    loginContainer.setMaxWidth(200);
    loginContainer.setMaxHeight(usernameField.prefHeight(-1) + loginButton.prefHeight(-1) + 20);

    // Set the maximum height and width of the loginButton
    loginButton.setMaxWidth(Double.MAX_VALUE);
    loginButton.setMaxHeight(Double.MAX_VALUE);

    // Apply styling to the loginContainer, loginText and loginButton
    loginContainer.getStyleClass().add("login-container");
    loginText.getStyleClass().add("login-title");
    loginButton.getStyleClass().add("login-button");


    // Add the VBox to the root of the scene
    getBorderPane().setCenter(loginContainer);
  }
}
