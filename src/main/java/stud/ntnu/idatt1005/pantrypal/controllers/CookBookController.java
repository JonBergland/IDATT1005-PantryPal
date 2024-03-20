package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.LinkedHashMap;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.RecipeRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.CookbookView;
import stud.ntnu.idatt1005.pantrypal.views.RecipeView;

import java.util.HashMap;
import java.util.Map;


/**
 * The controller for the CookBookView and RecipeView
 * Handles the logic for the CookBookView and RecipeView, in addition to updating the view.
 */
public class CookBookController extends Controller {

  /**
   * The view for the CookBookController
   */
  private final CookbookView view;
  private final RecipeRegister recipeRegister;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   * and also creates basic test data for the recipe register.
   *
   * @param viewManager the ViewManager for the application
   */
  public CookBookController(ViewManager viewManager) {
    super(viewManager);
    this.recipeRegister = new RecipeRegister();

    for (int i = 1; i <= 18; i++) {
      GroceryRegister groceries = new GroceryRegister();
      groceries.addGrocery(new Grocery("Grocery " + 1, 1, "category", null));
      groceries.addGrocery(new Grocery("Grocery " + 2, 1, "category", null));
      groceries.addGrocery(new Grocery("Grocery " + 3, 1, "category", null));
      StepRegister steps = new StepRegister();
      steps.addStep("Step 1");
      steps.addStep("Step 2");
      steps.addStep("Step 3");
      Recipe recipe = new Recipe("Recipe " + i, groceries, steps, null);
      recipeRegister.addRecipe(recipe);
    }
    this.view = new CookbookView(this);
    this.viewManager.addView(Route.COOKBOOK, this.view);
  }

  /**
   * Returns the LinkedHashMap in the recipeRegister.
   *
   * @return the LinkedHashMap in the recipeRegister.
   */
  public LinkedHashMap<String, Recipe> getRecipes() {
    return this.recipeRegister.getRegister();
  }

  /**
   * Adds a recipe to the recipeRegister.
   *
   * @param recipe the recipe to be added to the recipeRegister.
   */
  public void addRecipe(Recipe recipe) {
    this.recipeRegister.addRecipe(recipe);
  }

  /**
   * Opens a recipe in the RecipeView, and sets the view to RecipeView.
   *
   * @param recipe the recipe to be opened in the RecipeView.
   */
  public void openRecipe(Recipe recipe) {
    this.viewManager.addView(Route.RECIPE, new RecipeView(this, recipe));
    this.viewManager.setView(Route.RECIPE);
  }

}
