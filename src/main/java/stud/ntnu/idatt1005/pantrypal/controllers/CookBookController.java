package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.CookBookView;

/**
 * Controller class for the CookBookView. This class is responsible for handling the logic for the
 * CookBookView.
 */
public class CookBookController extends Controller {

  /**
   * The view for the CookBookController
   */
  private final CookBookView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public CookBookController(ViewManager viewManager) {
    super(viewManager);
    this.view = new CookBookView(this);
    this.viewManager.addView(Route.COOKBOOK, this.view);
  }
}
