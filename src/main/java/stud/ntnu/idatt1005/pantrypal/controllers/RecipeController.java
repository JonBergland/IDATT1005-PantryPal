package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.RecipeView;

public class RecipeController extends Controller {

  private final RecipeView view;

  public RecipeController(ViewManager viewManager) {
    super(viewManager);
    this.view = new RecipeView(this);
    this.viewManager.addView(Route.RECIPE, view);
  }
}
