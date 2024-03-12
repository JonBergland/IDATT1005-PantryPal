package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class AddRecipeView extends View {

  private final AddRecipeController controller;

  public AddRecipeView(AddRecipeController controller) {
    super(Route.ADD_RECIPE);
    this.controller = controller;
  }
}
