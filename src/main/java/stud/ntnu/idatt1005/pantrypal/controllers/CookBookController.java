package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.LinkedHashMap;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.RecipeRegister;
import stud.ntnu.idatt1005.pantrypal.registers.ShelfRegister;
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
  private final ShelfRegister shelfRegister;
  private final GroceryRegister shoppingListRegister;
  private final ShoppingListController shoppingListController;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public CookBookController(ViewManager viewManager, ShoppingListController shoppingListController,
      PantryController pantryController) {
    super(viewManager);
    this.recipeRegister = new RecipeRegister();
    this.shelfRegister = pantryController.getRegister();
    this.shoppingListRegister = shoppingListController.getRegister();
    this.shoppingListController = shoppingListController;

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

  public void addGroceriesToShoppingList(Recipe recipe) {
    for (Map.Entry<String, Grocery> entry : recipe.getRecipeGroceries().getRegister().entrySet()) {
      String groceryName = entry.getKey();
      String groceryCategory = entry.getValue().getCategory();
      int quantityNeeded = entry.getValue().getQuantity();

      Grocery[] shelfGroceries = shelfRegister.getAllGroceries();
      int quantityInShelf = 0;

      for (Grocery grocery : shelfGroceries) {
        if (grocery.getKey().equals(groceryName)) {
          quantityInShelf += grocery.getQuantity();
        }
      }

      Grocery shoppingListGrocery = null;
      int quantityInShoppingList;
      try {
        shoppingListGrocery = shoppingListRegister.getGrocery(groceryName);
        quantityInShoppingList = shoppingListGrocery.getQuantity();
      } catch(Exception e){
        quantityInShoppingList = 0;
      }


      int totalQuantityAvailable = quantityInShelf + quantityInShoppingList;

      if (quantityNeeded > totalQuantityAvailable) {
        int quantityToAdd = quantityNeeded - totalQuantityAvailable;
        if (shoppingListGrocery != null) {
          shoppingListGrocery.setQuantity(shoppingListGrocery.getQuantity() + quantityToAdd);
        } else {
          shoppingListRegister.addGrocery(
              new Grocery(groceryName, quantityToAdd, groceryCategory, null));
        }
      }
    }

    shoppingListController.rerender();
  }
}
