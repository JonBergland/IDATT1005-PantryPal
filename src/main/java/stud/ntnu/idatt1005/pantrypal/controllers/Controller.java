package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.PantryPal;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;

/**
 * The Root controller class for the application. This class is responsible for handling the logic
 * for that all controllers use.
 */
public class Controller {

  /**
   * The view manager for the application
   */
  final ViewManager viewManager;

  /**
   * Creates an instance of the controller
   *
   * @param viewManager The view manager for the application
   */
  public Controller(ViewManager viewManager) {
    this.viewManager = viewManager;
  }

  /**
   * Navigates to the given route
   *
   * @param route The route to navigate to
   */
  public void onNavLinkPress(Route route) {
    viewManager.setView(route);
  }

  public boolean isLoggedIn() {
    return PantryPal.userName != null && !PantryPal.userName.isBlank();
  }

  public void logOut(){
    PantryPal.userName = null;
    viewManager.init();
  }
}
