package stud.ntnu.idatt1005.pantrypal.models;

import java.util.Date;

/**
 * This is an entity class representing groceries It contains the name, quantity, category and
 * expiration date of the grocery
 */
public class Grocery extends Model{

  private int quantity;
  private final String shelf;
  private boolean isChecked = false;
  /**
   * Constructor for the Grocery class
   *
   * @param name           the name of the grocery
   * @param quantity       the quantity of the grocery
   * @param shelf       the category of the grocery
   */
  public Grocery(String name, int quantity, String shelf, boolean isChecked) {
    super(name);
    this.quantity = quantity;
    this.shelf = shelf;
    this.isChecked = isChecked;
  }

  /**
   * Deep-copy constructor for the Grocery class
   *
   * @param grocery the grocery to be copied
   */
  public Grocery(Grocery grocery) {
    super(grocery.getKey());
    this.quantity = grocery.getQuantity();
    this.shelf = grocery.getShelf();
    this.isChecked = grocery.getChecked();
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Get the quantity of the grocery
   *
   * @return the quantity of the grocery
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Get the category of the grocery
   *
   * @return the category of the grocery
   */
  public String getShelf() {
    return shelf;
  }

  /**
   * Get the name of the grocery
   *
   * @return the name of the grocery
   */
  public String getName() {
    return getKey();
  }

  /**
   * Get the checked status of the grocery
   *
   * @return the checked status of the grocery
   */
  public boolean getChecked() {
    return isChecked;
  }

  /**
   * Set the checked status of the grocery
   *
   * @param checked the checked status of the grocery
   */
  public void setChecked(boolean checked) {
    this.isChecked = checked;
  }

  @Override
  public String toString() {
    return "Name: " + getKey() + ", Quantity: " + quantity + ", Shelf: " + shelf;
  }
}
