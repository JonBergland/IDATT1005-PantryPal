package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import stud.ntnu.idatt1005.pantrypal.PantryPal;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.RecipeRegister;
import stud.ntnu.idatt1005.pantrypal.registers.ShelfRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.SQL;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.CookbookView;
import stud.ntnu.idatt1005.pantrypal.views.RecipeView;

/**
 * The controller for the CookBookView and RecipeView
 * Handles the logic for the CookBookView and RecipeView, in addition to updating the view.
 */
public class CookBookController extends Controller implements Observer {

  private final RecipeRegister recipeRegister;
  private List<Recipe> currentSearch;
  private final ShelfRegister shelfRegister;
  private final GroceryRegister shoppingListRegister;
  private final ShoppingListController shoppingListController;
  private final CookbookView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   * and also creates basic test data for the recipe register.
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

    this.currentSearch = recipeRegister.getRegister().values().stream().toList();

    this.view = new CookbookView(this);
    this.view.addObserver(this);
    this.viewManager.addView(Route.COOKBOOK, view);

    this.load();

    this.rerender();
  }

  private void load(){
    String query = "SELECT * FROM recipe";
    List<Map<String, Object>> recipesFromDB = SQL.executeQuery(query);

    for(Map<String, Object> recipeFromDB : recipesFromDB){
      GroceryRegister groceries = new GroceryRegister();
      StepRegister steps = new StepRegister();

      String id = recipeFromDB.get("id").toString();
      String name = recipeFromDB.get("name").toString();
      String description = recipeFromDB.get("description").toString();
      String image = recipeFromDB.get("image").toString();

      String groceriesQuery = "SELECT * FROM recipe_grocery WHERE recipe_id = ?";
      List<Map<String, Object>> groceriesFromDB = SQL.executeQuery(groceriesQuery, id);

      for(Map<String, Object> groceryFromDB : groceriesFromDB){
        String groceryName = groceryFromDB.get("grocery_name").toString();
        int quantity = (int) groceryFromDB.get("quantity");

        String groceryQuery = "SELECT * FROM grocery WHERE name = ?";
        List<Map<String, Object>> groceryDataFromDB = SQL.executeQuery(groceryQuery, groceryName);

        String unit = groceryDataFromDB.getFirst().get("unit").toString();
        //TODO: Fix shelf
        Grocery grocery = new Grocery(groceryName, quantity, unit, "", false);
        groceries.addGrocery(grocery);
      }

      String stepsQuery = "SELECT * FROM step WHERE recipe_id = ?";
      List<Map<String, Object>> stepsFromDB = SQL.executeQuery(stepsQuery, id);

      for(Map<String, Object> stepFromDB : stepsFromDB){
        String step = stepFromDB.get("description").toString();
        steps.addStep(step);
      }

      String favoriteQuery = "SELECT * FROM recipe_favorite WHERE recipe_id = ? AND user_name = ?";
      List<Map<String, Object>> favorite = SQL.executeQuery(favoriteQuery, id, PantryPal.userName);
      boolean isFavorite = !favorite.isEmpty();

      Recipe recipe = new Recipe(name, description, groceries, steps, image, isFavorite);
      this.recipeRegister.addRecipe(recipe);
    }
  }

  /**
   * Returns the LinkedHashMap in the recipeRegister.
   *
   * @return the LinkedHashMap in the recipeRegister.
   */
  public Map<String, Recipe> getRecipes() {
    return this.recipeRegister.getRegister();
  }

  public List<Recipe> getCurrentSearch() {
    return currentSearch;
  }
  /**
   * Updates the observer with the button that was pressed and the object affected
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    if (!(object instanceof Recipe recipe)) {
      throw new IllegalArgumentException("Object is not of type Recipe");
    }
    switch (buttonEnum) {
      case OPEN_RECIPE:
        openRecipe(recipe);
        break;
      case ADD_TO_SHOPPING_LIST:
        addGroceriesToShoppingList(recipe);
        break;
      case EDIT:
        toggleIsFavorite(recipe);
        break;
      case ADD:
        recipeRegister.addRecipe(recipe);
        view.render();
        this.viewManager.setView(Route.COOKBOOK);
        break;
      case REMOVE:
        recipeRegister.removeRecipe(recipe);
        currentSearch = recipeRegister.getRegister().values().stream().toList();
        view.render();
        viewManager.setView(Route.COOKBOOK);
        break;
      default:
        break;
    }

  }

  /**
   * Updates the observer with the button that was pressed
   *
   * @param buttonEnum the button that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum) {
    if (Objects.requireNonNull(buttonEnum) == ButtonEnum.ADD) {
      openAddRecipe();
    } else {
      throw new UnsupportedOperationException("Button not supported: " + buttonEnum);
    }
  }

  /**
   * Opens the AddRecipeView and sets the view to AddRecipeView.
   * Creates a new AddRecipeController and AddRecipeView and
   * set the view to AddRecipeView.
   */
  private void openAddRecipe() {
    new AddRecipeController(this.viewManager, this);
    this.viewManager.setView(Route.ADD_RECIPE);
  }

  public void searchRecipes(String search) {
    currentSearch = recipeRegister.searchRecipes(search);
    view.render();
  }
  /**
   * Opens a recipe in the RecipeView, and sets the view to RecipeView.
   *
   * @param recipe the recipe to be opened in the RecipeView.
   */
  private void openRecipe(Recipe recipe) {
    RecipeView recipeView = new RecipeView(this, recipe);
    recipeView.addObserver(this);
    this.viewManager.addView(Route.RECIPE, recipeView);
    this.viewManager.setView(Route.RECIPE);
  }

  /**
   * Adds the groceries from a recipe to the shopping list.
   * This method gets the grocery from the recipe and checks
   * if the grocery is already in the shopping list or pantry.
   * If the grocery is in the pantry, it checks if the quantity
   * is enough. If not, it adds the difference to the shopping list.
   * If the grocery is not in the pantry, it adds the grocery to the
   * shopping list.
   *
   * @param recipe the recipe to add groceries from.
   */
  private void addGroceriesToShoppingList(Recipe recipe) {
    for (Map.Entry<String, Grocery> entry : recipe.getRecipeGroceries().getRegister().entrySet()) {
      String groceryName = entry.getKey();
      String groceryShelf = entry.getValue().getShelf();
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
          //TODO: Fix unit
          shoppingListRegister.addGrocery(
              new Grocery(groceryName, quantityToAdd, "g", groceryShelf, false));
        }
      }
    }

    shoppingListController.rerender();
  }

  /**
   * Toggles the favorite status of a recipe.
   *
   * @param recipe the recipe to toggle the favorite status of.
   */
  private void toggleIsFavorite(Recipe recipe) {
    recipe.toggleIsFavorite();
    view.render();
  }

  public void rerender(){
    view.render();
  }
}
