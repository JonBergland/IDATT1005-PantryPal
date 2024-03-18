package stud.ntnu.idatt1005.pantrypal.models;

import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;

import java.util.HashMap;

public class Shelf extends Model {
    private String name;
    private final GroceryRegister groceryRegister;
    public Shelf(String name){
        this.name = name;
        this.groceryRegister = new GroceryRegister();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addGrocery(Grocery grocery) {
        groceryRegister.addGrocery(grocery);
    }

    public void removeGrocery(Grocery grocery) {
        groceryRegister.removeGrocery(grocery);
    }

    public HashMap<String, Grocery> getGroceries() {
        return groceryRegister.getRegister();
    }
}
