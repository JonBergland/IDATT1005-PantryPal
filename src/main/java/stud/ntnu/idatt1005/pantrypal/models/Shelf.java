package stud.ntnu.idatt1005.pantrypal.models;

import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;

import java.util.HashMap;

/**
 * This class represents a Shelf in the PantryPal application.
 * A Shelf has a name and a register of groceries.
 */
public class Shelf extends Model {
    private String name;
    private final GroceryRegister groceryRegister;

    /**
     * Constructor for Shelf class
     * @param name the name of the shelf
     */
    public Shelf(String name){
        this.name = name;
        this.groceryRegister = new GroceryRegister();
    }

    /**
     * Sets the name of the shelf
     * @param name the new name of the shelf
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the shelf
     * @return the name of the shelf
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a grocery item to the shelf
     * @param grocery the grocery item to be added
     */
    public void addGrocery(Grocery grocery) {
        groceryRegister.addGrocery(grocery);
    }

    /**
     * Removes a grocery item from the shelf
     * @param grocery the grocery item to be removed
     */
    public void removeGrocery(Grocery grocery) {
        groceryRegister.removeGrocery(grocery);
    }

    /**
     * Gets the register of groceries on the shelf
     * @return the register of groceries on the shelf
     */
    public GroceryRegister getGroceryRegister() {
        return groceryRegister;
    }

    /**
     * Gets the map of groceries in the register of the shelf
     * @return a HashMap of groceries on the shelf
     */
    public HashMap<String, Grocery> getGroceries() {
        return groceryRegister.getRegister();
    }
}
