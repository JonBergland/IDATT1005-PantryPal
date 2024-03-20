package stud.ntnu.idatt1005.pantrypal.models;

import org.junit.jupiter.api.Test;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGrocery {
  @Test
  void testGetShelf() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "Fridge", false);

    // Test the getCategory() method
    assertEquals("Fridge", grocery.getShelf());
  }

  @Test
  void testGetChecked() {
    // Create a Grocery object
    boolean checked = true;
    Grocery grocery = new Grocery("Apple", 5, "Fridge", checked);

    // Test the getExpirationDate() method
    assertEquals(checked, grocery.getChecked());
  }

  @Test
  void testGetName() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "Fridge", false);

    // Test the getName() method
    assertEquals("Apple", grocery.getKey());
  }

  @Test
  void testGetQuantity() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "Fridge", false);

    // Test the getQuantity() method
    assertEquals(5, grocery.getQuantity());
  }
}
