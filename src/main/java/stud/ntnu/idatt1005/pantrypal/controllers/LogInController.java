package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.PantryPal;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.LogInView;

/**
 * Controller class for the LogInView. This class is responsible for handling the logic for the
 * LogInView.
 */
public class LogInController extends Controller {

  /**
   * The view for the LogInController
   */
  private final LogInView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public LogInController(ViewManager viewManager) {
    super(viewManager);
    this.view = new LogInView(this);
    this.viewManager.addView(Route.LOGIN, this.view);
  }

  public void logIn(String username) {
    PantryPal.userName = username;

    viewManager.init();
  }

  public void logOut() {
    PantryPal.userName = null;
  }
}
