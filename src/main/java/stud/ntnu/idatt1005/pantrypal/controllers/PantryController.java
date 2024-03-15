package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.PantryView;

/**
 * Controller for the PantryView. This class is responsible for handling the logic for the
 * PantryView.
 */
public class PantryController extends Controller {

  /**
   * The view for the PantryController.
   */
  private final PantryView view;

  /**
   * Constructor for the PantryController.
   *
   * @param viewManager The view manager for the application.
   */
  public PantryController(ViewManager viewManager) {
    super(viewManager);
    this.view = new PantryView(this);
    this.viewManager.addView(Route.PANTRY, this.view);
  }
}