package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Grocery;

/**
 * Represents a register of groceries. The register is a map of grocery names and grocery objects.
 */
public class GroceryRegister extends Register<Grocery> {

  /**
   * Constructor for the GroceryRegister class.
   */
  public GroceryRegister() {
    super();
  }

  /**
   * Deep-copy constructor for the GroceryRegister class.
   *
   * @param register the register to be used
   */
  public GroceryRegister(GroceryRegister register) {
    super(register);
  }

  protected String getErrorMessage() {
    return "Grocery does not exist in register";
  }

  /**
   * Get a grocery from the register.
   *
   * @param name the name of the grocery to be retrieved
   * @return the grocery with the specified name
   */
  public Grocery getGrocery(String name) throws IllegalArgumentException {
    return super.getModel(name);
  }

  /**
   * Check if the register contains a grocery.
   *
   * @param name the name of the grocery to be checked
   */
  public boolean containsGrocery(String name) {
    return super.containsModel(name);
  }

  /**
   * Add a grocery to the register.
   *
   * @param grocery the grocery to be added
   * @throws IllegalArgumentException if the grocery already exists in the register
   */
  public void addGrocery(Grocery grocery) {
    super.addModel(grocery);
  }

  /**
   * Remove a grocery from the register.
   *
   * @param grocery the grocery to be removed
   */
  public void removeGrocery(Grocery grocery) {
    super.removeModel(grocery);
  }
}
