package stud.ntnu.idatt1005.pantrypal.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRecipe {

  static Recipe recipe;
  static GroceryRegister groceries;
  static StepRegister steps;


  @BeforeAll
  static void setUp() {
    Grocery apple = new Grocery("apple", 1, "fruit", null);
    Grocery banana = new Grocery("banana", 2, "fruit", null);
    groceries = new GroceryRegister();
    groceries.addItem(apple);
    groceries.addItem(banana);

    steps = new StepRegister();
    steps.addStep("Step 1");
    steps.addStep("Step 2");

    recipe = new Recipe("Apple Banana Smoothie", groceries, steps, null);

  }

  @Test
  @DisplayName("Test getName()")
  void testGetName() {
    assertEquals("Apple Banana Smoothie", recipe.getKey());
  }

  @Test
  @DisplayName("Test getRecipeGroceries()")
  void testGetRecipeGroceries() {
    assertEquals(2, recipe.getRecipeGroceries().getRegisterMap().size());
    assertEquals(groceries.getGrocery("apple"),
        recipe.getRecipeGroceries().getRegisterMap().get("apple"));
    assertEquals(groceries.getGrocery("banana"),
        recipe.getRecipeGroceries().getRegisterMap().get("banana"));
  }

  @Test
  @DisplayName("Test getRecipeSteps()")
  void testGetSteps() {
    List<String> expected = new ArrayList<>();
    expected.add("Step 1");
    expected.add("Step 2");
    assertEquals(expected, recipe.getRecipeSteps());
  }
}