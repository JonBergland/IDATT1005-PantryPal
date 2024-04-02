package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.ShoppingListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
   * The controller related to the pantry.
   * Used to update the pantry view when groceries are added to the shopping list.
   */
  private final PantryController pantryController;

  /**
   * Constructs a new ShoppingListController with a given view manager.
   *
   * @param viewManager The view manager for the application.
   */
  public ShoppingListController(ViewManager viewManager, PantryController pantryController) {
    super(viewManager);
    this.register = new GroceryRegister();
    this.pantryController = pantryController;
    // Add some groceries to the register
    String fridge = "Fridge";
    String cupboard = "Cupboard";
    this.register.addGrocery(new Grocery("Milk", 1, fridge, false));
    this.register.addGrocery(new Grocery("Bread", 1, cupboard, false));
    this.register.addGrocery(new Grocery("Butter", 1, fridge, false));
    this.register.addGrocery(new Grocery("Cheese", 1, fridge, false));
    this.register.addGrocery(new Grocery("Egg", 1, fridge, false));
    this.register.addGrocery(new Grocery("Apple", 1, cupboard, false));
    this.register.addGrocery(new Grocery("Banana", 1, cupboard, false));

    this.view = new ShoppingListView(this);
    this.view.addObserver(this);
    rerender();
    this.viewManager.addView(Route.SHOPPING_LIST, view);
  }

  public GroceryRegister getRegister() {
    return this.register;
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
      case ADD_TO_SHOPPING_LIST:
        register.addGrocery(grocery);
        rerender();
        break;
      case REMOVE:
        register.removeGrocery(grocery);
        rerender();
        break;
      default:
        throw new IllegalArgumentException("Button not supported by class");
    }
  }

  /**
   * Updates the observer based on the button pressed.
   * If the button pressed is ADD_TO_PANTRY, the groceries that are checked are added to the pantry and removed from the shopping list.
   * The view is re-rendered after the groceries are added to the pantry.
   *
   * @param buttonEnum the button that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum){
    if (Objects.requireNonNull(buttonEnum) == ButtonEnum.ADD_TO_PANTRY) {
      addGroceriesToPantry();
      rerender();
    } else {
      throw new IllegalArgumentException("Button not supported by class");
    }
  }

  public void addGroceriesToPantry(){
    List<Grocery> groceriesToRemove = new ArrayList<>();
    for(Grocery grocery : register.getRegister().values()){
      if (grocery.getChecked()) {
        pantryController.addGrocery(grocery.getShelf(), grocery.getName(), grocery.getQuantity());
        groceriesToRemove.add(grocery);
      }
    }
    for(Grocery grocery : groceriesToRemove){
      register.removeGrocery(grocery);
    }
  }

  public void rerender(){
    view.render(this.register);
  }
}
