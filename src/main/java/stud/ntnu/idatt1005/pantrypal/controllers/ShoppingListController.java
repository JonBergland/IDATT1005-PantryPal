package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.Map;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.ShoppingListView;

/**
 * Controller class for the ShoppingListView.
 * Handles the logic for the ShoppingListView, including managing the grocery register and updating the view.
 */
public class ShoppingListController extends Controller implements Observer {

  /**
   * The view associated with this controller.
   */
  private final ShoppingListView view;

  /**
   * The register holding the groceries.
   */
  private final GroceryRegister register;

  /**
   * Constructs a new ShoppingListController with a given view manager.
   *
   * @param viewManager The view manager for the application.
   */
  public ShoppingListController(ViewManager viewManager) {
    super(viewManager);
    this.register = new GroceryRegister();
    // Add some groceries to the register
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

  /**
   * Returns the grocery register.
   *
   * @return the grocery register
   */
  public Map<String, Grocery> getRegister() {
    return register.getRegister();
  }

  /**
   * Updates the observer based on the button pressed and the grocery item associated with the action.
   * If the button pressed is ADD, the grocery item is added to the register and the view is re-rendered.
   * If the button pressed is REMOVE, the grocery item is removed from the register and the view is re-rendered.
   *
   * @param buttonEnum the button that was pressed
   * @param object     the grocery item associated with the action
   * @throws IllegalArgumentException if the object is not of type Grocery
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    if (!(object instanceof Grocery grocery)) {
      throw new IllegalArgumentException("Object is not of type Grocery");
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
}
