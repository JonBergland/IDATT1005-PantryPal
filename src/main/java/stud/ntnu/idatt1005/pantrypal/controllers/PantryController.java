package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.ShelfRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.PantryView;

import java.util.Collection;

/**
 * Controller for the PantryView. This class is responsible for handling the logic for the
 * PantryView.
 */
public class PantryController extends Controller {

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
    this.viewManager.addView(Route.PANTRY, this.view);
    this.register = new ShelfRegister();

    // Add some shelves dummy-data to the register
    Shelf fridge = new Shelf("Fridge");
    Shelf cupboard = new Shelf("Cupboard");
    this.register.addShelf(fridge);
    this.register.addShelf(cupboard);

    this.view.render();
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
   * Adds a shelf to the register.
   */
  public void addShelf() {
    shelfCount++;
    Shelf shelf = new Shelf("New Shelf " + shelfCount);
    register.addShelf(shelf);
    view.render();
  }

  /**
   * Deletes the specified shelf.
   * @param shelf the shelf to delete
   */
  public void deleteShelf(Shelf shelf) {
    register.removeShelf(shelf);
    view.render();
  }

  /**
   * Edits the name of the specified shelf.
   * @param shelf the shelf to edit
   * @param name the new name of the shelf
   */
  public void editShelfName(Shelf shelf, String name) {
    shelf.setName(name);
    view.render();

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
    view.render();
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
      shelf = register.getShelf(shelfName);
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
    view.render();
  }
}
