package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.RecipeView;

/**
 * Controller class for the RecipeView. This class is responsible for handling the logic for the
 * RecipeView.
 */
public class RecipeController extends Controller {

  /**
   * The view for the RecipeController.
   */
  private final RecipeView view;

  /**
   * Constructor for the RecipeController.
   *
   * @param viewManager The view manager for the application.
   */
  public RecipeController(ViewManager viewManager) {
    super(viewManager);
    this.view = new RecipeView(this);
    this.viewManager.addView(Route.RECIPE, view);
  }
}
