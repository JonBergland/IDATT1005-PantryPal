package stud.ntnu.idatt1005.pantrypal.registers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;

import static org.junit.jupiter.api.Assertions.*;

class ShelfRegisterTest {
  ShelfRegister shelfRegister;

  @BeforeEach
  void setUp() {
    shelfRegister = new ShelfRegister();

    Shelf shelf = new Shelf("Fridge");
    shelfRegister.addShelf(shelf);
  }

  @Test
  void getErrorMessage() {
    assertEquals("Shelf does not exist in register", shelfRegister.getErrorMessage());
  }

  @Test
  void getShelfByName() {
    assertEquals("Fridge", shelfRegister.getShelfByName("Fridge").getName());
  }

  @Test
  void addShelf() {
    Shelf shelf = new Shelf("Freezer");
    shelfRegister.addShelf(shelf);
    assertEquals("Freezer", shelfRegister.getShelfByName("Freezer").getName());
  }

  @Test
  void removeShelf() {
    Shelf shelf = new Shelf("Freezer");
    shelfRegister.addShelf(shelf);
    shelfRegister.removeShelf(shelf);
    assertNull(shelfRegister.getShelfByName("Freezer"));
  }

  @Test
  void getAllGroceries() {
    assertEquals(0, shelfRegister.getAllGroceries().length);
  }
}