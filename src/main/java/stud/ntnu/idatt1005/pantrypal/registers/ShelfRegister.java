package stud.ntnu.idatt1005.pantrypal.registers;

import java.util.List;
import java.util.Map;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.utils.SQL;

/**
 * This class represents a register of shelves in the PantryPal application. It extends the Register
 * class with type parameter Shelf. It contains methods for adding and removing shelves, and getting
 * all groceries from all shelves.
 */
public class ShelfRegister extends Register<Shelf> {

  /**
   * Constructor for ShelfRegister class.
   * Calls the parent constructor.
   */
  public ShelfRegister() {
    super();
  }

  /**
   * Returns an error message when a shelf does not exist in the register.
   *
   * @return a string error message.
   */
  protected String getErrorMessage() {
    return "Shelf does not exist in register";
  }

  /**
   * Returns the shelf from the register that corresponds with name given.
   *
   * @param nameOfShelf the name of the shelf to be retrieved.
   *
   * @return the Shelf object.
   */
  public Shelf getShelfByName(String nameOfShelf) {
    for (Shelf shelf : this.getRegister().values()) {
      if (shelf.getName().equals(nameOfShelf)) {
        return shelf;
      }
    }
    return null;
  }

  /**
   * Adds a shelf to the register.
   *
   * @param shelf the Shelf object to be added.
   */
  public void addShelf(Shelf shelf) {
    this.addModel(shelf);
  }

  /**
   * Removes a shelf from the register.
   *
   * @param shelf the Shelf object to be removed.
   */
  public void removeShelf(Shelf shelf) {
    this.removeModel(shelf);
  }

  /**
   * Returns all groceries from all shelves in the register.
   *
   * @return an array of Grocery objects.
   */
  public Grocery[] getAllGroceries() {
    return this.getRegister().values().stream()
        .flatMap(shelf -> shelf.getGroceries().values().stream())
        .toArray(Grocery[]::new);
  }

  /**
   * Loads all shelves and groceries from the database for the specified user.
   *
   * @param username the username of the user to load shelves for.
   */
  public void load(String username) {
    String shelfQuery = "SELECT * FROM pantry_shelf WHERE user_name = ?";
    List<Map<String, Object>> shelves = SQL.executeQuery(shelfQuery, username);

    for (Map<String, Object> shelf : shelves) {
      int shelfId = (int) shelf.get("id");
      String shelfKey = String.valueOf(shelfId);
      String shelfName = shelf.get("name").toString();

      Shelf s = new Shelf(shelfKey, shelfName);

      String groceryQuery = "SELECT g.*, psg.quantity AS quantity FROM pantry_shelf_grocery psg "
          + "INNER JOIN grocery g ON g.name = psg.grocery_name "
          + "WHERE psg.pantry_shelf_id = ?";
      List<Map<String, Object>> groceries = SQL.executeQuery(groceryQuery, shelfId);

      for (Map<String, Object> grocery : groceries) {

        String groceryName = grocery.get("name").toString();
        int groceryQuantity = (int) grocery.get("quantity");
        String groceryUnit = grocery.get("unit").toString();


        Grocery g = new Grocery(groceryName, groceryQuantity, groceryUnit, shelfName, false);
        s.addGrocery(g);
      }
      this.addShelf(s);
    }
  }
}
