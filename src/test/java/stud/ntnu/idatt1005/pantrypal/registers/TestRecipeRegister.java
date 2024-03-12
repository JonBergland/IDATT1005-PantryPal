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
    Grocery apple = new Grocery("apple", 1, "fruit", null);
    Grocery banana = new Grocery("banana", 2, "fruit", null);
    groceries = new GroceryRegister();
    groceries.addItem(apple);
    groceries.addItem(banana);

    steps = new StepRegister();
    steps.addStep("Step 1");
    steps.addStep("Step 2");

    recipe = new Recipe("Apple Banana Smoothie", groceries, steps);
    recipeRegister = new RecipeRegister();
    recipeRegister.addItem(recipe);
  }

  @Nested
  @DisplayName("Positive tests for RecipeRegister")
  class PositiveTestsRecipeRegister {
    @Test
    @DisplayName("Test getRegister()")
    public void testGetRegister() {
      assertEquals(1, recipeRegister.getRegisterMap().size());
      assertEquals(recipe, recipeRegister.getRegisterMap().get("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test getRecipeByName()")
    public void testGetRecipeByName() {
      assertEquals(recipe, recipeRegister.getItem("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test addRecipe() with recipe as parameter")
    public void testAddRecipeWithRecipeAsParameter() {
      Recipe newRecipe = new Recipe("Fruit salad", groceries, steps);
      recipeRegister.addItem(newRecipe);
      assertEquals(2, recipeRegister.getRegisterMap().size());
      assertEquals(newRecipe, recipeRegister.getItem("Fruit salad"));
    }

    @Test
    @DisplayName("Test addRecipe() with name, groceries and steps as parameters")
    void testAddRecipeWithRecipeFieldsAsParameter() {
      recipeRegister.addRecipe("Fruit salad", groceries, steps);
      assertEquals(2, recipeRegister.getRegisterMap().size());
      assertEquals("Fruit salad", recipeRegister.getItem("Fruit salad").getKey());
    }

    @Test
    @DisplayName("Test removeRecipe()")
    void testRemoveRecipe() {
      recipeRegister.removeItem(recipe);
      assertNull(recipeRegister.getRegisterMap().get("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test updateRecipe() with recipe as parameter")
    void testUpdateRecipeWithRecipeAsParameter() {
      steps.addStep("Step 3");
      Recipe newRecipe = new Recipe("Apple Banana Smoothie", groceries, steps);
      recipeRegister.updateRecipe(newRecipe);
      assertEquals(1, recipeRegister.getRegisterMap().size());
      assertEquals(newRecipe, recipeRegister.getRegisterMap().get("Apple Banana Smoothie"));
    }

    @Test
    @DisplayName("Test updateRecipe() with name, groceries and steps as parameters")
    void testUpdateRecipeWithRecipeFieldsAsParameter() {
      steps.addStep("Step 3");
      recipeRegister.updateRecipe("Apple Banana Smoothie", groceries, steps);
      assertEquals(1, recipeRegister.getRegisterMap().size());
      assertEquals("Apple Banana Smoothie", recipeRegister.getItem("Apple Banana Smoothie").getKey());
    }
  }

  @Nested
  @DisplayName("Negative tests for RecipeRegister")
  class NegativeTestsRecipeRegister {
    @Test
    @DisplayName("Test getRecipeByName() with non-existing recipe")
    void testGetRecipeByNameWithNonExistingRecipe() {
      try {
        recipeRegister.getItem("Fruit salad");
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe does not exist in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test addRecipe() with existing recipe")
    void testAddRecipeWithExistingRecipe() {
      try {
        recipeRegister.addItem(recipe);
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe already exists in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test addRecipe() with existing recipe name")
    void testAddRecipeWithExistingRecipeName() {
      try {
        recipeRegister.addRecipe("Apple Banana Smoothie", groceries, steps);
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe already exists in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test removeRecipe() with non-existing recipe")
    void testRemoveRecipeWithNonExistingRecipe() {
      try {
        Recipe fakeRecipe = new Recipe("Fruit salad", groceries, steps);
        recipeRegister.removeItem(fakeRecipe);
      } catch (IllegalArgumentException e) {
        assertEquals("Item does not exist in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test updateRecipe() with non-existing recipe")
    void testUpdateRecipeWithNonExistingRecipe() {
      try {
        Recipe newRecipe = new Recipe("Fruit salad", groceries, steps);
        recipeRegister.updateRecipe(newRecipe);
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe does not exist in register", e.getMessage());
      }
    }

    @Test
    @DisplayName("Test updateRecipe() with non-existing recipe name")
    void testUpdateRecipeWithNonExistingRecipeName() {
      try {
        recipeRegister.updateRecipe("Fruit salad", groceries, steps);
      } catch (IllegalArgumentException e) {
        assertEquals("Recipe does not exist in register", e.getMessage());
      }
    }
  }

}
