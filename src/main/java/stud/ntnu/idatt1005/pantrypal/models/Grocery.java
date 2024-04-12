package stud.ntnu.idatt1005.pantrypal.models;

import java.util.Date;

/**
 * This is an entity class representing groceries It contains the name, quantity, category and
 * expiration date of the grocery
 */
public class Grocery extends Model{

  private int quantity;
  private String unit;
  private String shelf;
  private boolean isChecked = false;
  /**
   * Constructor for the Grocery class
   *
   * @param name           the name of the grocery
   * @param quantity       the quantity of the grocery
   * @param shelf       the category of the grocery
   */
  public Grocery(String name, int quantity, String unit, String shelf, boolean isChecked) {
    super(name);
    this.quantity = quantity;
    this.unit = unit;
    this.shelf = shelf;
    this.isChecked = isChecked;

    if(this.shelf.isEmpty()){
      this.shelf = "Unassigned";
    }
  }

  /**
   * Deep-copy constructor for the Grocery class
   *
   * @param grocery the grocery to be copied
   */
  public Grocery(Grocery grocery) {
    super(grocery.getKey());
    this.quantity = grocery.getQuantity();
    this.unit = grocery.getUnit();
    this.shelf = grocery.getShelf();
    this.isChecked = grocery.getChecked();

    if(this.shelf.isEmpty()){
      this.shelf = "Unassigned";
    }
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
   * Get the unit of the grocery
   *
   * @return the unit of the grocery
   */
  public String getUnit() {
    return unit;
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
