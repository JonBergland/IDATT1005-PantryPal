package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * A class that extends View and creates a view for the shopping list.
 * The goal is to provide a dedicated view for users to view their shopping list.
 * This class is associated with an ShoppingListController to handle the logic
 * and actions related to the shopping list.
 */
public class ShoppingListView extends View {

  /**
   * The controller responsible for managing the logic and
   * actions associated with the shopping list functionality.
   */
  private final ShoppingListController controller;

  /**
   * Constructor for ShoppingListView.
   *
   * @param controller The controller for the view.
   */
  public ShoppingListView(ShoppingListController controller) {
    super(controller, Route.SHOPPING_LIST);
    this.controller = controller;
  }
}
