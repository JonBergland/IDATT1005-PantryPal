package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.ShelfRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.PantryView;

import java.util.Collection;
import java.util.Objects;

/**
 * Controller for the PantryView. This class is responsible for handling the logic for the
 * PantryView.
 */
public class PantryController extends Controller implements Observer {

  /**
   * The view for the PantryController.
   */
  private final PantryView view;

  private final ShelfRegister register;

  private int shelfCount = 0;

  /**
   * Constructor for the PantryController.
   *
   * @param viewManager The view manager for the application.
   */
  public PantryController(ViewManager viewManager) {
    super(viewManager);
    this.view = new PantryView(this);
    this.view.addObserver(this);
    this.viewManager.addView(Route.PANTRY, this.view);
    this.register = new ShelfRegister();

    // Add some shelves dummy-data to the register
    Shelf fridge = new Shelf("Fridge");
    Shelf cupboard = new Shelf("Cupboard");
    this.register.addShelf(fridge);
    this.register.addShelf(cupboard);

    rerender();
  }

  /**
   * Returns the register in the controller. In this case, the register is a ShelfRegister.
   * @return the register in the controller
   */
  public ShelfRegister getRegister() {
    return register;
  }

  /**
   * Returns all shelves in the register.
   * @return an array of Shelf objects
   */
  public Shelf[] getShelves() {
    Collection<Shelf> shelves = register.getRegister().values();

    return shelves.toArray(new Shelf[0]);
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
        try {
          addGrocery(grocery.getShelf(), grocery.getName(), grocery.getQuantity());
          rerender();
          break;
        } catch (IllegalArgumentException e) {
          break;
        }
      case REMOVE:
        try {
          Shelf shelf = register.getShelfByName(grocery.getShelf());
          deleteGrocery(shelf, grocery);
          rerender();
          break;
        } catch (IllegalArgumentException e) {
          break;
        }
      default:
        break;
    }
  }

  /**
   * Updates the observer based on the button pressed.
   *
   * @param buttonEnum the button that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum){
    if (Objects.requireNonNull(buttonEnum) == ButtonEnum.ADD_TO_PANTRY) {
      rerender();
    } else {
      throw new IllegalArgumentException("Button not supported by class");
    }
  }

  /**
   * Adds a shelf to the register.
   */
  public void addShelf() {
    shelfCount++;
    Shelf shelf = new Shelf("New Shelf " + shelfCount);
    register.addShelf(shelf);
    rerender();
  }

  /**
   * Deletes the specified shelf.
   * @param shelf the shelf to delete
   */
  public void deleteShelf(Shelf shelf) {
    register.removeShelf(shelf);
    rerender();
  }

  /**
   * Edits the name of the specified shelf.
   * @param shelf the shelf to edit
   * @param name the new name of the shelf
   */
  public void editShelfName(Shelf shelf, String name) {
    shelf.setName(name);
    rerender();

  }

  /**
   * Returns all groceries from the specified shelf.
   * @param shelf the shelf to get the groceries from
   * @return an array of Grocery objects
   */
  public Grocery[] getGroceries(Shelf shelf) {
    return shelf.getGroceries().values().toArray(new Grocery[0]);
  }

  /**
   * Adds a grocery item to the shelf.
   * If the grocery item already exists in the shelf, the quantity of the grocery item is updated.
   * @param shelf the shelf to add the grocery item to
   * @param name the name of the grocery item
   * @param amount the amount of the grocery item
   */
  public void addGrocery(Shelf shelf, String name, int amount) {
    if(shelf.getGroceries().containsKey(name)) {
      GroceryRegister groceryRegister = shelf.getGroceryRegister();

      int oldAmount = groceryRegister.getGrocery(name).getQuantity();
      shelf.getGroceryRegister().getGrocery(name).setQuantity(oldAmount + amount);
    } else {
      Grocery grocery = new Grocery(name, amount, shelf.getName(), false);
      shelf.addGrocery(grocery);
    }
    rerender();
  }

  /**
   * Adds a grocery item to the shelf.
   * If the shelf does not exist in the register, a new shelf is created
   * and the grocery item is added to the shelf.
   * @param shelfName the name of the shelf
   * @param name the name of the grocery item
   * @param amount the amount of the grocery item
   */
  public void addGrocery(String shelfName, String name, int amount) {
    Shelf shelf = null;
    try {
      shelf = register.getShelfByName(shelfName);
    } catch (IllegalArgumentException e) {
      shelf = new Shelf(shelfName);
    } finally {
      //TODO Make name of models (ie. Shelf, Grocery, etc.) lowercase when used as keys in registers
      if (shelf == null) {
        shelf = new Shelf(shelfName);
      }
      register.addShelf(shelf);
      addGrocery(shelf, name, amount);
    }
  }

  /**
   * Removes the grocery item from the shelf.
   * If the shelf does not exist in the register, an IllegalArgumentException is thrown.
   * @param shelf the shelf from the grocery is to be removed
   * @param grocery the grocery item to be removed
   */
  public void deleteGrocery(Shelf shelf, Grocery grocery) {
    shelf.removeGrocery(grocery);
    rerender();
  }

  /**
   * Renders the view with the updated data.
   */
  public void rerender() {
    view.render(getShelves());
  }
}
