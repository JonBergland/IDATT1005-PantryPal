package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.LinkedHashMap;
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
    this.register.addGrocery(new Grocery("Milk", 1, "Dairy", null));
    this.register.addGrocery(new Grocery("Bread", 1, "Bread", null));
    this.register.addGrocery(new Grocery("Butter", 1, "Dairy", null));
    this.register.addGrocery(new Grocery("Cheese", 1, "Dairy", null));
    this.register.addGrocery(new Grocery("Egg", 1, "Dairy", null));
    this.register.addGrocery(new Grocery("Apple", 1, "Fruit", null));
    this.register.addGrocery(new Grocery("Banana", 1, "Fruit", null));

    this.view = new ShoppingListView(this);
    this.viewManager.addView(Route.SHOPPING_LIST, view);
  }

  public GroceryRegister getRegister() {
    return this.register;
  }

  /**
   * Updates the observer
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    Grocery grocery;
    if (object instanceof Grocery) {
      grocery = (Grocery) object;
    } else {
      throw new IllegalArgumentException("Object is not of type Model");
    }
    switch (buttonEnum) {
      case ADD:
        register.addGrocery(grocery);
        view.render();
        break;
      case REMOVE:
        register.removeGrocery(grocery);
        view.render();
        break;
      default:
        System.out.println("Button not supported by class");
        break;
    }
  }

  public void rerender(){
    view.render();
  }
}
