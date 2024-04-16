package stud.ntnu.idatt1005.pantrypal.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRecipe {

  static Recipe recipe;
  static GroceryRegister groceries;
  static StepRegister steps;
  static String description;


  @BeforeEach
  void setUp() {
    Grocery apple = new Grocery("apple", 1, "g", "Fridge", false);
    Grocery banana = new Grocery("banana", 2, "g", "Fridge", false);
    groceries = new GroceryRegister();
    groceries.addGrocery(apple);
    groceries.addGrocery(banana);

    steps = new StepRegister();
    steps.addStep("Step 1");
    steps.addStep("Step 2");

    description = "Dette er en oppskrift på en smoothie.";

    recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps, null, false);

  }

  @Test
  @DisplayName("Test getName()")
  void testGetName() {
    assertEquals("Apple Banana Smoothie", recipe.getKey());
  }

  @Test
  @DisplayName("Test getRecipeGroceries()")
  void testGetRecipeGroceries() {
    assertEquals(2, recipe.getRecipeGroceries().getRegister().size());
    assertEquals(groceries.getGrocery("apple"),
            recipe.getRecipeGroceries().getRegister().get("apple"));
    assertEquals(groceries.getGrocery("banana"),
            recipe.getRecipeGroceries().getRegister().get("banana"));
  }

  @Test
  @DisplayName("Test getRecipeSteps()")
  void testGetSteps() {
    List<String> expected = new ArrayList<>();
    expected.add("Step 1");
    expected.add("Step 2");
    assertEquals(expected, recipe.getRecipeSteps());
  }

  @Test
  @DisplayName("Test getIsFavorite()")
  void testGetIsFavorite() {
    assertFalse(recipe.getIsFavorite());
  }

  @Test
  @DisplayName("Test alterIsFavorite()")
  void testToggleIsFavorite() {
    recipe.toggleIsFavorite();
    assertTrue(recipe.getIsFavorite());
  }

  @Test
  @DisplayName("Test getDescription()")
  void testGetDescription() {
    assertEquals(description, recipe.getDescription());
  }

  @Test
  @DisplayName("Test getImagePath()")
  void testGetImagePath() {
    assertNull(recipe.getImagePath());
  }
}