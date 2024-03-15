package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Model;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.Register;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.ShoppingListView;

/**
 * Controller class for the ShoppingListView. This class is responsible for handling the logic for
 * the ShoppingListView.
 */
public class ShoppingListController extends Controller implements Observer {

  /**
   * The view for the ShoppingListController.
   */
  private final ShoppingListView view;

  private final GroceryRegister register;

  /**
   * Constructor for the ShoppingListController.
   *
   * @param viewManager The view manager for the application.
   */
  public ShoppingListController(ViewManager viewManager) {
    super(viewManager);
    this.register = new GroceryRegister();
    this.register.addItem(new Grocery("Milk", 1, "Dairy", null));
    this.register.addItem(new Grocery("Bread", 1, "Bread", null));

    this.view = new ShoppingListView(this);
    this.viewManager.addView(Route.SHOPPING_LIST, view);
  }

  public Register getRegister() {
    return register;
  }

  /**
   * Updates the observer
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    Model model;
    if (object instanceof Model) {
      model = (Model) object;
    } else {
      throw new IllegalArgumentException("Object is not of type Model");
    }
    switch (buttonEnum) {
      case ADD:
        register.addItem(model);
        view.updateView();
        break;
      case REMOVE:
        register.removeItem(model);
        view.updateView();
        break;
      default:
        System.out.println("Button not supported by class");
        break;
    }
  }
}
