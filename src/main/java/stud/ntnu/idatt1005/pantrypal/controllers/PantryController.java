package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.ShelfRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.PantryView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

    this.view.render();
  }

  public ShelfRegister getRegister() {
    return register;
  }

  public Shelf[] getShelves() {
    Collection<Shelf> shelves = register.getRegister().values();

    return shelves.toArray(new Shelf[0]);
  }

  public void addShelf() {
    shelfCount++;
    Shelf shelf = new Shelf("New Shelf " + shelfCount);
    register.addShelf(shelf);
    view.render();
  }

  public void deleteShelf(Shelf shelf) {
    register.removeShelf(shelf);
    view.render();
  }

  public void editShelfName(Shelf shelf, String name) {
    shelf.setName(name);
    view.render();

  }

  public Grocery[] getGroceries(Shelf shelf) {
    return shelf.getGroceries().values().toArray(new Grocery[0]);
  }

  public void addGrocery(Shelf shelf, String name, int amount)
      throws ParseException {
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

  public void deleteGrocery(Shelf shelf, Grocery grocery) {
    shelf.removeGrocery(grocery);
    view.render();
  }
}
