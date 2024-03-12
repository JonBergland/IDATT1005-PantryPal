package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Model;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;


/**
 * This is a register class for recipes.
 * It contains a register of recipes structured in a hashmap.
 * Goal: act as a register for recipes.
 */
public class RecipeRegister extends Register {
  /**
   * Constructor for the RecipeRegister class.
   * Initializes the recipe register.
   */
  public RecipeRegister() {
    super();
  }

  /**
   * Deep-copy constructor for the RecipeRegister class.
   * @param register the register to be used.
   */
  public RecipeRegister(RecipeRegister register) {
    super(register);
  }

  /**
   * Add a recipe to the recipe register.
   *
   * @param model the recipe to be added to the recipe register.
   */
  @Override
  public void addItem(Model model) throws IllegalArgumentException {
    Recipe recipe = (Recipe) model;
    if (super.registerMap.containsKey(recipe.getKey())) {
      throw new IllegalArgumentException("Recipe already exists in register");
    }
    super.registerMap.put(recipe.getKey(), recipe);
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
    if (super.registerMap.containsKey(name)) {
      throw new IllegalArgumentException("Recipe already exists in register");
    }
    super.registerMap.put(name, new Recipe(name, groceries, steps));
  }

  /**
   * Update a recipe in the recipe register.
   *
   * @param recipe the recipe to be updated in the recipe register.
   */
  public void updateRecipe(Recipe recipe)
      throws IllegalArgumentException {
    if (!super.registerMap.containsKey(recipe.getKey())) {
      throw new IllegalArgumentException("Recipe does not exist in register");
    }
    super.registerMap.replace(recipe.getKey(), recipe);
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
    if (!super.registerMap.containsKey(name)) {
      throw new IllegalArgumentException("Recipe does not exist in register");
    }
    super.registerMap.replace(name, new Recipe(name, groceries, steps));
  }
}

