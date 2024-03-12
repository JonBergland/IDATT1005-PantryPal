package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.ShoppingListView;

public class ShoppingListController extends Controller {
   private final ShoppingListView view;
    public ShoppingListController(ViewManager viewManager) {
        super(viewManager);
        this.view = new ShoppingListView(this);
        this.viewManager.addView(Route.SHOPPING_LIST, view);
    }
}
