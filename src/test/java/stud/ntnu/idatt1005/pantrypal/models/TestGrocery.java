package stud.ntnu.idatt1005.pantrypal.models;

import org.junit.jupiter.api.Test;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGrocery {
  @Test
  void testGetCategory() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "Fruit", new Date());

    // Test the getCategory() method
    assertEquals("Fruit", grocery.getCategory());
  }

  @Test
  void testGetExpirationDate() {
    // Create a Grocery object
    Date expirationDate = new Date();
    Grocery grocery = new Grocery("Apple", 5, "Fruit", expirationDate);

    // Test the getExpirationDate() method
    assertEquals(expirationDate, grocery.getExpirationDate());
  }

  @Test
  void testGetName() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "Fruit", new Date());

    // Test the getName() method
    assertEquals("Apple", grocery.getKey());
  }

  @Test
  void testGetQuantity() {
    // Create a Grocery object
    Grocery grocery = new Grocery("Apple", 5, "Fruit", new Date());

    // Test the getQuantity() method
    assertEquals(5, grocery.getQuantity());
  }
}
