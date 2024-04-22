package stud.ntnu.idatt1005.pantrypal.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGrocery {
  @Test
  void testGetShelf() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", false);

    // Test the getCategory() method
    assertEquals("Fridge", grocery.getShelf());
  }

  @Test
  void testShelfIsEmpty() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "", false);

    // Test the getCategory() method
    assertEquals("Unassigned", grocery.getShelf());
  }


  @Test
  void testGetChecked() {
    // Create a Grocery object
    boolean checked = true;
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", checked);

    // Test the getExpirationDate() method
    assertEquals(checked, grocery.getChecked());
  }

  @Test
  void testGetKey() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", false);

    // Test the getName() method
    assertEquals("Apple", grocery.getKey());
  }

  @Test
  void testGetName() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", false);

    // Test the getName() method
    assertEquals("Apple", grocery.getName());
  }

  @Test
  void testGetQuantity() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", false);

    // Test the getQuantity() method
    assertEquals(5, grocery.getQuantity());
  }

  @Test
  void testSetQuantity() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", false);

    // Test the setQuantity() method
    grocery.setQuantity(10);
    assertEquals(10, grocery.getQuantity());
  }

  @Test
  void setChecked() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "g", "Fridge", false);

    // Test the setChecked() method
    grocery.setChecked(true);
    assertTrue(grocery.getChecked());
  }
}
