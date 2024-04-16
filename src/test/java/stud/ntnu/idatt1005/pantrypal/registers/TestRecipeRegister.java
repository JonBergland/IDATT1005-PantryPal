package stud.ntnu.idatt1005.pantrypal.registers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;

import static org.junit.jupiter.api.Assertions.*;

class TestRecipeRegister {
  private static RecipeRegister recipeRegister;
  private static Recipe recipe;
  private static GroceryRegister groceries;
  private static StepRegister steps;

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

    recipe = new Recipe("Apple Banana Smoothie", "", groceries, steps, null, false);
    recipeRegister = new RecipeRegister();
    recipeRegister.addRecipe(recipe);
  }

  @Nested
  @DisplayName("Positive tests for RecipeRegister")
  class PositiveTestsRecipeRegister {
    @Test
    @DisplayName("Test getRegister()")
    void testGetRegister() {
      assertEquals(1, recipeRegister.getRegister().size());
      assertEquals(recipe, recipeRegister.getRegister().get("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test getRecipeByName()")
    void testGetRecipeByName() {
      assertEquals(recipe, recipeRegister.getRecipe("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test addRecipe() with recipe as parameter")
    void testAddRecipeWithRecipeAsParameter() {
      Recipe newRecipe = new Recipe("Fruit salad", "", groceries, steps, null, false);
      recipeRegister.addRecipe(newRecipe);
      assertEquals(2, recipeRegister.getRegister().size());
      assertEquals(newRecipe, recipeRegister.getRecipe("Fruit salad"));
    }

    @Test
    @DisplayName("Test removeRecipe()")
    void testRemoveRecipe() {
      recipeRegister.removeRecipe(recipe);
      assertNull(recipeRegister.getRegister().get("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test updateRecipe() with recipe as parameter")
    void testUpdateRecipeWithRecipeAsParameter() {
      steps.addStep("Step 3");
      Recipe newRecipe = new Recipe("Apple Banana Smoothie", "", groceries, steps, null, false);
      recipeRegister.addRecipe(newRecipe);
      assertEquals(1, recipeRegister.getRegister().size());
      assertEquals(newRecipe, recipeRegister.getRegister().get("Apple Banana Smoothie"));
    }
  }

  @Nested
  @DisplayName("Negative tests for RecipeRegister")
  class NegativeTestsRecipeRegister {
    @Test
    @DisplayName("Test getRecipeByName() with non-existing recipe")
    void testGetRecipeByNameWithNonExistingRecipe() {
      try {
        recipeRegister.getRecipe("Fruit salad");
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe does not exist in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test addRecipe() with existing recipe")
    void testAddRecipeWithExistingRecipe() {
      try {
        recipeRegister.addRecipe(recipe);
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe already exists in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test removeRecipe() with non-existing recipe")
    void testRemoveRecipeWithNonExistingRecipe() {
      try {
        Recipe fakeRecipe = new Recipe("Fruit salad","", groceries, steps, null, false);
        recipeRegister.removeRecipe(fakeRecipe);
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe does not exist in register", e.getMessage());
      }
    }
  }

}
