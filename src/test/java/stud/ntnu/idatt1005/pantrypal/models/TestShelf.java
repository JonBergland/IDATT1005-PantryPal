package stud.ntnu.idatt1005.pantrypal.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;

import static org.junit.jupiter.api.Assertions.*;

class TestShelf {
  String name;
  Shelf shelf;
  Grocery grocery;

  @BeforeEach
  void setUp() {
    name = "Fridge";
    shelf = new Shelf(name);

    grocery = new Grocery("Apple", 5, "g", "Fridge", false);
    shelf.addGrocery(grocery);
  }

  @Test
  void testConstructor() {
    assertAll(
      () -> assertEquals(name, shelf.getName()),
      () -> assertEquals(GroceryRegister.class, shelf.getGroceryRegister().getClass()),
      () -> assertEquals(1, shelf.getGroceries().size()),
      () -> assertTrue(shelf.getGroceries().containsKey("Apple"))
    );
  }

  @Test
  void testConstructorWithKey() {
    String key = "1";
    Shelf newShelf = new Shelf(key, name);
    assertAll(
      () -> assertEquals(key, newShelf.getKey()),
      () -> assertEquals(name, newShelf.getName()),
      () -> assertEquals(GroceryRegister.class, newShelf.getGroceryRegister().getClass()),
      () -> assertEquals(0, newShelf.getGroceries().size())
    );
  }

  @Test
  void getName() {
    assertEquals(name, shelf.getName());
  }

  @Test
  void getGroceryRegister() {
    assertEquals(GroceryRegister.class, shelf.getGroceryRegister().getClass());
  }

  @Test
  void getGroceries() {
    assertAll(
      () -> assertEquals(1, shelf.getGroceries().size()),
      () -> assertTrue(shelf.getGroceries().containsKey("Apple"))
    );

  }

  @Test
  void setName() {
    String newName = "Freezer";
    shelf.setName(newName);
    assertEquals(newName, shelf.getName());
  }

  @Test
  void addGrocery() {
    Grocery grocery = new Grocery("Banana", 3, "g", "Fridge", false);
    shelf.addGrocery(grocery);
    assertAll(
      () -> assertEquals(2, shelf.getGroceries().size()),
      () -> assertTrue(shelf.getGroceries().containsKey("Banana"))
    );
  }

  @Test
  void removeGrocery() {
    shelf.removeGrocery(grocery);
    assertAll(
      () -> assertEquals(0, shelf.getGroceries().size()),
      () -> assertFalse(shelf.getGroceries().containsKey("Apple"))
    );
  }
}