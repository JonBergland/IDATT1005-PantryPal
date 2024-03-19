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
 * Controller class for the CookBookView. This class is responsible for handling the logic for the
 * CookBookView.
 */
public class CookBookController extends Controller {

  /**
   * The view for the CookBookController
   */
  private final CookbookView view;
  private final RecipeRegister recipeRegister;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
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

  public LinkedHashMap<String, Recipe> getRecipes() {
    return this.recipeRegister.getRegister();
  }

  public void addRecipe(Recipe recipe) {
    this.recipeRegister.addRecipe(recipe);
  }

  public void openRecipe(Recipe recipe) {
    this.viewManager.addView(Route.RECIPE, new RecipeView(this, recipe));
    this.viewManager.setView(Route.RECIPE);
  }

}
