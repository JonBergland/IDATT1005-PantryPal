package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.LogOutController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * A class that extends View and creates a view for logging out.
 * The goal is to provide a dedicated view for users to log out of the application.
 * This class is associated with an LogOutController to handle the logic
 * and actions related to logging out.
 */
public class LogOutView extends View {

  /**
   * The controller responsible for managing the logic and
   * actions associated with logging out.
   */
  private final LogOutController controller;

  /**
   * Constructor for LogOutView.
   *
   * @param controller The controller for the view.
   */
  public LogOutView(LogOutController controller) {
    super(controller, Route.LOGOUT);
    this.controller = controller;
  }
}
