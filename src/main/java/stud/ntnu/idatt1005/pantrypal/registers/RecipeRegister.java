package stud.ntnu.idatt1005.pantrypal.registers;

import java.util.HashMap;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;


/**
 * This is a register class for recipes.
 * It contains a register of recipes structured in a hashmap.
 * Goal: act as a register for recipes.
 */
public class RecipeRegister {

  /**
   * A register of recipes.
   */
  HashMap<String, Recipe> recipeRegister;

  /**
   * Constructor for the RecipeRegister class.
   * Initializes the recipe register.
   */
  public RecipeRegister() {
    recipeRegister = new HashMap<>();
  }

  /**
   * Get the recipe register.
   *
   * @return the recipe register.
   */
  public HashMap<String, Recipe> getRegister() {
    return recipeRegister;
  }

  /**
   * Get a recipe by name.
   *
   * @param name the name of the recipe.
   * @return the recipe with the given name.
   */
  public Recipe getRecipeByName(String name) throws IllegalArgumentException {
    if (!recipeRegister.containsKey(name)) {
      throw new IllegalArgumentException("Recipe does not exist in register");
    }
    return recipeRegister.get(name);
  }

  /**
   * Add a recipe to the recipe register.
   *
   * @param recipe the recipe to be added to the recipe register.
   */
  public void addRecipe(Recipe recipe) throws IllegalArgumentException {
    if (recipeRegister.containsKey(recipe.getName())) {
      throw new IllegalArgumentException("Recipe already exists in register");
    }
    recipeRegister.put(recipe.getName(), recipe);
  }

  /**
   * Add a recipe to the recipe register.
   *
   * @param name the name of the recipe.
   * @param groceries the groceries needed for the recipe.
   * @param steps the steps needed to make the recipe.
   */
  public void addRecipe(String name, GroceryRegister groceries, StepRegister steps)
      throws IllegalArgumentException {
    if (recipeRegister.containsKey(name)) {
      throw new IllegalArgumentException("Recipe already exists in register");
    }
    recipeRegister.put(name, new Recipe(name, groceries, steps));
  }

  /**
   * Remove a recipe from the recipe register.
   *
   * @param name the name of the recipe to be removed from the recipe register.
   */
  public void removeRecipe(String name)
      throws IllegalArgumentException {
    if (!recipeRegister.containsKey(name)) {
      throw new IllegalArgumentException("Recipe does not exist in register");
    }
    recipeRegister.remove(name);
  }

  /**
   * Update a recipe in the recipe register.
   *
   * @param recipe the recipe to be updated in the recipe register.
   */
  public void updateRecipe(Recipe recipe)
      throws IllegalArgumentException {
    if (!recipeRegister.containsKey(recipe.getName())) {
      throw new IllegalArgumentException("Recipe does not exist in register");
    }
    recipeRegister.replace(recipe.getName(), recipe);
  }

  /**
   * Update a recipe in the recipe register.
   *
   * @param name the name of the recipe.
   * @param groceries the groceries needed for the recipe.
   * @param steps the steps needed to make the recipe.
   */
  public void updateRecipe(String name, GroceryRegister groceries, StepRegister steps)
      throws IllegalArgumentException{
    if (!recipeRegister.containsKey(name)) {
      throw new IllegalArgumentException("Recipe does not exist in register");
    }
    recipeRegister.replace(name, new Recipe(name, groceries, steps));
  }
}

