package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class ShoppingListView extends View {

  private final ShoppingListController controller;

  public ShoppingListView(ShoppingListController controller) {
    super(controller, Route.SHOPPING_LIST);
    this.controller = controller;
  }
}
