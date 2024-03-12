package stud.ntnu.idatt1005.pantrypal.registers;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Model;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestGroceryRegister {
  GroceryRegister groceryRegister;
  Grocery grocery1;
  Grocery grocery2;

  @BeforeEach
  void setUp() {
    groceryRegister = new GroceryRegister();

    grocery1 = new Grocery("Milk", 1, "Dairy", null);
    grocery2 = new Grocery("Bread", 2, "Bakery", null);

    groceryRegister.addItem(grocery1);
    groceryRegister.addItem(grocery2);
  }

  @Nested
  class Constructor {
    @Test
    @DisplayName("Test of deep-copy constructor")
    void constructor() {
      GroceryRegister newGroceryRegister = new GroceryRegister(groceryRegister);

      String groceryName = "Eggs";
      Grocery grocery3 = new Grocery(groceryName, 12, "Dairy", null);
      newGroceryRegister.addItem(new Grocery(grocery3));

      assertAll(
          () -> assertEquals(grocery3.toString(), newGroceryRegister.getGrocery(groceryName).toString(), "Grocery not added to register"),
          () -> assertThrows(IllegalArgumentException.class, () -> groceryRegister.getGrocery(groceryName), "Grocery added to original register")
      );
    }
  }

  @Nested
  class GetGrocery {
    @Test
    @DisplayName("Test of get grocery")
    void getGrocery() {
      assertAll("Get grocery",
          () -> assertEquals(grocery1.toString(), groceryRegister.getGrocery(grocery1.getKey()).toString(), "Incorrect grocery retrieved"),
          () -> assertEquals(grocery2.toString(), groceryRegister.getGrocery(grocery2.getKey()).toString(), "Incorrect grocery retrieved"))
      ;
    }

    @Test
    @DisplayName("Test of get grocery with grocery that does not exist")
    void getGroceryDoesNotExist() {
      assertThrows(IllegalArgumentException.class, () -> groceryRegister.getGrocery("Eggs"), "Grocery exists in register");
    }
  }

  @Nested
  class GetRegister {
    @Test
    @DisplayName("Test of get register")
    void getRegister() {
      Map<String, Model> actual = groceryRegister.getRegisterMap();

      assertAll("Get register",
          () -> assertEquals(grocery1.toString(), actual.get(grocery1.getKey()).toString(), "Incorrect grocery retrieved"),
          () -> assertEquals(grocery2.toString(), actual.get(grocery2.getKey()).toString(), "Incorrect grocery retrieved"));
    }
  }


  @Nested
  class AddGrocery {
    @Test
    @DisplayName("Test of add grocery with new grocery")
    void addGrocery() {
      Grocery grocery3 = new Grocery("Eggs", 12, "Dairy", null);
      groceryRegister.addItem(grocery3);

      assertEquals(grocery3.toString(), groceryRegister.getGrocery(grocery3.getKey()).toString(), "Grocery not added");
    }

    @Test
    @DisplayName("Test of add grocery with grocery that already exists")
    void addGroceryAlreadyExists() {
      Grocery grocery3 = new Grocery("Milk", 1, "Dairy", null);
      assertThrows(IllegalArgumentException.class, () -> groceryRegister.addItem(grocery3), "Grocery does not exist in register");
    }

    @Test
    @DisplayName("Test of add grocery with deep copy of grocery")
    void addGroceryDeepCopyOfGrocery() {
      Grocery grocery3 = new Grocery("Eggs", 12, "Dairy", null);
      groceryRegister.addItem(grocery3);

      assertNotEquals(grocery3, groceryRegister.getGrocery(grocery3.getKey()), "Grocery is not a deep copy");
    }
  }

  @Nested
  class RemoveGrocery {
    @Test
    @DisplayName("Test of remove grocery")
    void removeGrocery() {
      String gKey = grocery1.getKey();
      groceryRegister.removeItem(grocery1);

      assertThrows(IllegalArgumentException.class, () -> groceryRegister.getGrocery(gKey), "Grocery exists in register");
    }

    @Test
    @DisplayName("Test of remove grocery with grocery that does not exist")
    void removeGroceryDoesNotExist() {
      Grocery fakeGrocery = new Grocery("Eggs", 12, "Dairy", null);
      assertThrows(IllegalArgumentException.class, () -> groceryRegister.removeItem(fakeGrocery)
          , "Grocery exists in register");
    }

    @Test
    @DisplayName("Test of sort groceries in ascending order")
    void sortGroceriesAscending(){
      List<Model> sortedGroceries = groceryRegister.sortItemsAscending();

      assertAll("Sort groceries in ascending order",
          () -> assertEquals(grocery2.toString(), sortedGroceries.getFirst().toString(), "Grocery not sorted in ascending order"),
          () -> assertEquals(grocery1.toString(), sortedGroceries.get(1).toString(), "Grocery not sorted in ascending order"));
    }

    @Test
    @DisplayName("Test of sort groceries in descending order")
    void sortGroceriesDescending(){
      List<Model> sortedGroceries = groceryRegister.sortItemsDescending();

      assertAll("Sort groceries in descending order",
          () -> assertEquals(grocery1.toString(), sortedGroceries.getFirst().toString(), "Grocery not sorted in descending order"),
          () -> assertEquals(grocery2.toString(), sortedGroceries.get(1).toString(), "Grocery not sorted in descending order"));
    }
  }
}
