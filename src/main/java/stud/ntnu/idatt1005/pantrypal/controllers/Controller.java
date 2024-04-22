package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.PantryPal;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;

/**
 * The Root controller class for the application. This class is responsible for handling the logic
 * for that all controllers use. This is logIn and logOut functionality and navigation between
 * different views.
 */
public class Controller {

  /**
   * The view manager for the application.
   */
  public final ViewManager viewManager;

  /**
   * Creates an instance of the controller, and sets the viewManager.
   *
   * @param viewManager The view manager for the application
   */
  public Controller(ViewManager viewManager) {
    this.viewManager = viewManager;
  }

  /**
   * Navigates to the view the given route represents.
   *
   * @param route The route to navigate to
   */
  public void onNavLinkPress(Route route) {
    viewManager.setView(route);
  }

  /**
   * Checks if the user is logged in.
   *
   * @return true if the user is logged in, false otherwise
   */
  public boolean isLoggedIn() {
    return PantryPal.userName != null && !PantryPal.userName.isBlank();
  }

  /**
   * Logs out the user and navigates to the reloads the initial application, without login.
   */
  public void logOut() {
    PantryPal.userName = null;
    viewManager.init();
  }
}
