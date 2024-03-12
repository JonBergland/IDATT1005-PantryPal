package stud.ntnu.idatt1005.pantrypal.registers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;

/**
 * Represents a register of groceries.
 * The register is a map of grocery names and grocery objects.
 */
public class GroceryRegister {
  Map<String, Grocery> register;

  /**
   * Constructor for the GroceryRegister class
   */
  public GroceryRegister() {
    this.register = new HashMap<>();
  }

  /**
   * Deep-copy constructor for the GroceryRegister class
   * @param register the register to be used
   */
  public GroceryRegister(GroceryRegister register) {
    this.register = new HashMap<>(register.getRegister());
  }

  /**
   * Get a grocery from the register
   *
   * @param name the name of the grocery to be retrieved
   * @return the grocery with the specified name
   */
  public Grocery getGrocery(String name) throws IllegalArgumentException {
    if (!register.containsKey(name)) {
      throw new IllegalArgumentException("Grocery does not exist in register");
    }
    return register.get(name);
  }

  /**
   * Get a deep copy of the register map
   * @return the register map
   */
  public Map<String, Grocery> getRegister() {
    return new HashMap<>(register);
  }


  /**
   * Add a grocery to the register
   *
   * @param grocery the grocery to be added
   * @throws IllegalArgumentException if the grocery already exists in the register
   */
  public void addGrocery(Grocery grocery) throws IllegalArgumentException {
    if (register.containsKey(grocery.getName())) {
      throw new IllegalArgumentException("Grocery already exists in register");
    }

    register.put(grocery.getName(), new Grocery(grocery));
  }

  /**
   * Remove a grocery from the register
   *
   * @param name the name of the grocery to be removed
   * @throws IllegalArgumentException if the grocery does not exist in the register
   */
  public void removeGrocery(String name) {
    if (!register.containsKey(name)) {
      throw new IllegalArgumentException("Grocery does not exist in register");
    }
    register.remove(name);
  }

  /**
   * Sorts the groceries in the register in ascending order based on their names.
   *
   * @return a list of groceries sorted in ascending order
   */
  public List<Grocery> sortGroceriesAscending() {
    List<Grocery> sortedGroceries = new ArrayList<>(register.values());
    sortedGroceries.sort(Comparator.comparing(Grocery::getName));
    return sortedGroceries;
  }

  /**
   * Sorts the groceries in the register in descending order based on their names.
   *
   * @return a list of groceries sorted in descending order
   */
  public List<Grocery> sortGroceriesDescending() {
    List<Grocery> sortedGroceries = new ArrayList<>(register.values());
    sortedGroceries.sort(Comparator.comparing(Grocery::getName).reversed());
    return sortedGroceries;
  }
}
