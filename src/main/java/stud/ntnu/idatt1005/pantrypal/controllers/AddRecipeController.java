package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.AddRecipeView;

public class AddRecipeController extends Controller {

  private final AddRecipeView view;

  public AddRecipeController(ViewManager viewManager) {
    super(viewManager);
    this.view = new AddRecipeView(this);
    this.viewManager.addView(Route.ADD_RECIPE, this.view);
  }
}
