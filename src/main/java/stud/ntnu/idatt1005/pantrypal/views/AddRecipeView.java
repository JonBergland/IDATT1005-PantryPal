package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * A class that extends View and creates a view for adding a recipe.
 * The goal is to provide a dedicated view for users to add a new recipe.
 * This class is associated with an AddRecipeController to handle the logic
 * and actions related to adding a recipe.
 */
public class AddRecipeView extends View {

  /**
   * The controller responsible for managing the logic and actions associated with adding a recipe.
   */
  private final AddRecipeController controller;

  /**
   * Constructor for AddRecipeView.
   *
   * @param controller The AddRecipeController associated with this view.
   */
  public AddRecipeView(AddRecipeController controller) {
    super(controller, Route.ADD_RECIPE);
    this.controller = controller;
  }
}
