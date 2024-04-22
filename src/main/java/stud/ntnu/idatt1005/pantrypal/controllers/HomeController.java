package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.HomeView;

/**
 * Controller class for the HomeView.
 * This class is responsible for handling the logic for theHomeView.
 */
public class HomeController extends Controller {

  /**
   * The view for the HomeController.
   */
  private final HomeView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller.
   *
   * @param viewManager the ViewManager for the application
   */
  public HomeController(ViewManager viewManager) {
    super(viewManager);
    this.view = new HomeView(this);
    this.viewManager.addView(Route.HOME, this.view);
  }
}
