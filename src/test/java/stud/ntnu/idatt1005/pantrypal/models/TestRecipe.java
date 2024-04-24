package stud.ntnu.idatt1005.pantrypal.models;

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
    Grocery apple = new Grocery("apple", 1, "pc","Fridge", false);
    Grocery banana = new Grocery("banana", 2, "pc", "Fridge", false);
    groceries = new GroceryRegister();
    groceries.addGrocery(apple);
    groceries.addGrocery(banana);

    steps = new StepRegister();
    steps.addStep("Step 1");
    steps.addStep("Step 2");

    description = "This is the recipe for a smoothie.";

    recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps, null, false);

  }

  @Test
  @DisplayName("Test validImagePath() with valid path to local file")
  void testValidImagePathValidLocalFile() {
    String path = "src/main/resources/Images/PantryPalLogo.png";
    Recipe recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps,
            path, false);
    assertEquals(path, recipe.getImagePath());
  }

  @Test
  @DisplayName("Test validImagePath() with invalid path to local file")
  void testValidImagePathInvalidLocalFile() {
    Recipe recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps,
            "src/main/resources/images/invalid.png", false);
    assertNull(recipe.getImagePath());
  }

  @Test
  @DisplayName("Test validImagePath() with valid path to URL")
  void testValidImagePathValidURL() {
    String url = "https://i.ntnu.no/documents/1305837853/1306916684/ntnu_hoeyde_eng."
            + "png/9130ea3c-828a-497e-b469-df0c54e16bb5?t=1578568440350";
    Recipe recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps,
            url, false);
    assertEquals(url, recipe.getImagePath());
  }

  @Test
  @DisplayName("Test validImagePath() with not-existing URL")
  void testValidImagePathInvalidURL() {
    Recipe recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps,
            "https://i.ntnu.no/documents/1305837853/1306916684/ntnu_hoeyde_eng."
                    + "png/9130e16bb5?t=1578568440350", false);
    assertNull(recipe.getImagePath());
  }

  @Test
  @DisplayName("Test validImagePath() with  URL that does not point to an image")
  void testValidImagePathInvalidURLNotImage() {
    Recipe recipe = new Recipe("Apple Banana Smoothie", description, groceries, steps,
            "https://www.ntnu.no", false);
    assertNull(recipe.getImagePath());
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
    recipe.toggleIsFavorite();
    assertFalse(recipe.getIsFavorite());
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