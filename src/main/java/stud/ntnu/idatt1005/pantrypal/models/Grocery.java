package stud.ntnu.idatt1005.pantrypal.models;

import java.util.Date;

/**
 * This is an entity class representing groceries It contains the name, quantity, category and
 * expiration date of the grocery
 */
public class Grocery {

  final String name;
  final int quantity;
  final String category;
  final Date expirationDate;

  /**
   * Constructor for the Grocery class
   *
   * @param name           the name of the grocery
   * @param quantity       the quantity of the grocery
   * @param category       the category of the grocery
   * @param expirationDate the expiration date of the grocery
   */
  public Grocery(String name, int quantity, String category, Date expirationDate) {
    this.name = name;
    this.quantity = quantity;
    this.category = category;
    this.expirationDate = expirationDate;
  }

  /**
   * Deep-copy constructor for the Grocery class
   *
   * @param grocery the grocery to be copied
   */
  public Grocery(Grocery grocery) {
    this.name = grocery.name;
    this.quantity = grocery.quantity;
    this.category = grocery.category;
    this.expirationDate = grocery.expirationDate;
  }

  /**
   * Get the name of the grocery
   *
   * @return the name of the grocery
   */
  public String getName() {
    return name;
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
  public String getCategory() {
    return category;
  }

  /**
   * Get the expiration date of the grocery
   *
   * @return the expiration date of the grocery
   */
  public Date getExpirationDate() {
    return expirationDate;
  }

  @Override
  public String toString() {
    return "Name: " + name + ", Quantity: " + quantity + ", Category: " + category
        + ", Expiration date: " + expirationDate;
  }

}
