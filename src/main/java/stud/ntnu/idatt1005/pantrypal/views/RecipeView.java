package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.RecipeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * This class represents the RecipeView in the application. It extends the View class and sets the
 * scene for the stage. The RecipeView is created with a specific viewType.
 */
public class RecipeView extends View {

  private final RecipeController controller;

  public RecipeView(RecipeController controller) {
    super(Route.RECIPE);
    this.controller = controller;
  }

}
