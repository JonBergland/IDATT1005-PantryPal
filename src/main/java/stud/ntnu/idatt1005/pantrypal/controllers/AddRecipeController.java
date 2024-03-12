package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.AddRecipeView;

/**
 * Controller class for the AddRecipeView. This class is responsible for handling the logic for the
 * AddRecipeView.
 */
public class AddRecipeController extends Controller {

  /**
   * The view for the AddRecipeController
   */
  private final AddRecipeView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public AddRecipeController(ViewManager viewManager) {
    super(viewManager);
    this.view = new AddRecipeView(this);
    this.viewManager.addView(Route.ADD_RECIPE, this.view);
  }
}
