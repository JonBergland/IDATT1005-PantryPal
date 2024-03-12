package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.ShoppingListView;

/**
 * Controller class for the ShoppingListView. This class is responsible for handling the logic for
 * the ShoppingListView.
 */
public class ShoppingListController extends Controller {

  /**
   * The view for the ShoppingListController.
   */
  private final ShoppingListView view;

  /**
   * Constructor for the ShoppingListController.
   *
   * @param viewManager The view manager for the application.
   */
  public ShoppingListController(ViewManager viewManager) {
    super(viewManager);
    this.view = new ShoppingListView(this);
    this.viewManager.addView(Route.SHOPPING_LIST, view);
  }
}
