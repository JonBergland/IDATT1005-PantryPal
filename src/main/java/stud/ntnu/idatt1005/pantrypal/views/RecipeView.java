package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.RecipeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * This class represents the RecipeView in the application. It extends the View class and sets the
 * scene for the stage. The RecipeView is created with a specific viewType.
 */
public class RecipeView extends View {

  /**
   * The controller responsible for managing the logic and
   * actions associated with the recipe functionality.
   */
  private final RecipeController controller;

  /**
   * Constructor for RecipeView.
   *
   * @param controller The controller for the view.
   */
  public RecipeView(RecipeController controller) {
    super(controller, Route.RECIPE);
    this.controller = controller;
  }

}
