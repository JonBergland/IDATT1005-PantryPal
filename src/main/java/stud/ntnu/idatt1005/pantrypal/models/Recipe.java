package stud.ntnu.idatt1005.pantrypal.models;

import java.util.List;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;


/**
 * This is an entity class representing a recipe.
 * It contains the name, groceries and steps of the recipe.
 * Goal: act as a model for a recipe.
 */
public class Recipe {

  /**
   * The name of the recipe.
   */
  private final String name;

  /**
   * The groceries needed for the recipe.
   */
  private final GroceryRegister recipeGroceries;

  /**
   * The steps needed to make the recipe.
   */
  private final StepRegister steps;

  /**
   * Constructor for the Recipe class.
   *
   * @param name the name of the recipe.
   * @param recipeGroceries the groceries needed for the recipe.
   * @param steps the steps needed to make the recipe.
   */
  public Recipe(String name, GroceryRegister recipeGroceries, StepRegister steps) {
    this.name = name;
    this.recipeGroceries = recipeGroceries;
    this.steps = steps;
  }

  /**
   * Get the name of the recipe.
   *
   * @return the name of the recipe.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the groceries needed for the recipe.
   *
   * @return the groceries needed for the recipe.
   */
  public GroceryRegister getRecipeGroceries() {
    return recipeGroceries;
  }

  /**
   * Get the steps needed to make the recipe.
   *
   * @return the steps needed to make the recipe.
   */
  public List<String> getRecipeSteps() {
    return steps.getSteps();
  }
}
