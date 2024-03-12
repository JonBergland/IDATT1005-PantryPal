package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Model;

/**
 * Represents a register of groceries.
 * The register is a map of grocery names and grocery objects.
 */
public class GroceryRegister extends Register{

  /**
   * Constructor for the GroceryRegister class
   */
  public GroceryRegister() {
    super();
  }

  /**
   * Deep-copy constructor for the GroceryRegister class
   * @param register the register to be used
   */
  public GroceryRegister(GroceryRegister register) {
    super(register);
  }

  /**
   * Get a grocery from the register
   *
   * @param name the name of the grocery to be retrieved
   * @return the grocery with the specified name
   */
  public Grocery getGrocery(String name) throws IllegalArgumentException {
    if (!getRegisterMap().containsKey(name)) {
      throw new IllegalArgumentException("Grocery does not exist in register");
    }
    return (Grocery) getItem(name);
  }

  /**
   * Add a grocery to the register
   *
   * @param model the model to be added
   * @throws IllegalArgumentException if the grocery already exists in the register
   */
  @Override
  public void addItem(Model model) throws IllegalArgumentException {
    Grocery grocery = (Grocery) model;
    if (getRegisterMap().containsKey(grocery.getKey())) {
      throw new IllegalArgumentException("Grocery already exists in register");
    }

    getRegisterMap().put(grocery.getKey(), new Grocery(grocery));
  }
}
