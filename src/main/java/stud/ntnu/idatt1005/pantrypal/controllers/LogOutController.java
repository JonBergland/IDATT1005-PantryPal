package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.LogOutView;

/**
 * Controller class for the LogOutView. This class is responsible for handling the logic for the
 * LogOutView.
 */
public class LogOutController extends Controller {

  /**
   * The view for the LogOutController
   */
  private final LogOutView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public LogOutController(ViewManager viewManager) {
    super(viewManager);
    this.view = new LogOutView(this);
    this.viewManager.addView(Route.LOGOUT, this.view);
  }
}
