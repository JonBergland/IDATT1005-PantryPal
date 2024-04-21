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
 * The controller for the CookBookView and RecipeView.
 * This class is responsible for handling the logic for the CookBookView and RecipeView.
 */
public class CookbookController extends Controller implements Observer {

  private final RecipeRegister recipeRegister;
  private List<Recipe> currentSearch;
  private final ShelfRegister shelfRegister;
  private final GroceryRegister shoppingListRegister;
  private final ShoppingListController shoppingListController;
  private final CookbookView view;

  /**
   * Constructor for the CookbookController. The constructor takes in a ViewManager, a
   * ShoppingListController, and a PantryController. The constructor creates a new RecipeRegister
   * and sets the shelfRegister and shoppingListRegister to the registers in the PantryController
   * and ShoppingListController. The constructor loads the recipes from the database if the user is
   * logged in. The currentSearch is set to all the recipes in the recipeRegister. The view is
   * created and added to the viewManager.
   *
   * @param viewManager            the ViewManager for the application
   * @param shoppingListController the ShoppingListController for the application
   * @param pantryController       the PantryController for the application
   */
  public CookbookController(ViewManager viewManager, ShoppingListController shoppingListController,
                            PantryController pantryController) {
    super(viewManager);
    this.recipeRegister = new RecipeRegister();
    this.shelfRegister = pantryController.getRegister();
    this.shoppingListRegister = shoppingListController.getRegister();
    this.shoppingListController = shoppingListController;

    if (this.isLoggedIn()) {
      this.load();
    }

    this.currentSearch = getRecipes().values().stream().toList();
    this.view = new CookbookView(this);
    this.view.addObserver(this);
    this.viewManager.addView(Route.COOKBOOK, view);

    this.rerender();
  }

  /**
   * Loads the recipes from the database. This method gets all the recipes from the database and
   * creates a new Recipe object for each recipe related to the user. The method also gets the
   * groceries and steps for each recipe and adds them to the Recipe object. The method also
   * checks if the recipe is a favorite for the user and sets the isFavorite boolean in the Recipe
   * object. The Recipe object is then added to the recipeRegister.
   */
  private void load() {
    String query = "SELECT * FROM recipe";
    List<Map<String, Object>> recipesFromDataBase = SQL.executeQuery(query);

    for (Map<String, Object> recipeFromDataBase : recipesFromDataBase) {
      GroceryRegister groceries = new GroceryRegister();
      StepRegister steps = new StepRegister();

      String id = recipeFromDataBase.get("id").toString();
      String name = recipeFromDataBase.get("name").toString();
      String description = recipeFromDataBase.get("description").toString();
      String image = recipeFromDataBase.get("image").toString();

      String groceriesQuery = "SELECT * FROM recipe_grocery WHERE recipe_id = ?";
      List<Map<String, Object>> groceriesFromDataBase = SQL.executeQuery(groceriesQuery, id);

      for (Map<String, Object> groceryFromDataBase : groceriesFromDataBase) {
        String groceryName = groceryFromDataBase.get("grocery_name").toString();
        int quantity = (int) groceryFromDataBase.get("quantity");

        String groceryQuery = "SELECT * FROM grocery WHERE name = ?";
        List<Map<String, Object>> groceryDataFromDataBase =
                SQL.executeQuery(groceryQuery, groceryName);

        String unit = groceryDataFromDataBase.getFirst().get("unit").toString();
        //TODO: Fix shelf
        Grocery grocery = new Grocery(groceryName, quantity, unit, "", false);
        groceries.addGrocery(grocery);
      }

      String stepsQuery = "SELECT * FROM step WHERE recipe_id = ?";
      List<Map<String, Object>> stepsFromDataBase = SQL.executeQuery(stepsQuery, id);

      for (Map<String, Object> stepFromDataBase : stepsFromDataBase) {
        String step = stepFromDataBase.get("description").toString();
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
   * Returns the register with recipes in the recipeRegister.
   *
   * @return the register with Recipes in the recipeRegister.
   */
  public Map<String, Recipe> getRecipes() {
    return this.recipeRegister.getRegister();
  }

  /**
   * Returns the current search list.
   *
   * @return the current search list.
   */
  public List<Recipe> getCurrentSearch() {
    return currentSearch;
  }

  /**
   * Updates the observer with the button that was pressed and the object affected. If
   * the object is a Recipe, it performs the action related to the buttonEnum. If the object is not
   * a Recipe, it throws an IllegalArgumentException.
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that is related to the button
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
      case EDIT_FAVORITE:
        toggleIsFavorite(recipe);
        break;
      case EDIT_RECIPE:
        AddRecipeController addRecipeController = new AddRecipeController(
                this.viewManager, this);
        addRecipeController.setRecipeToAddRecipeView(recipe);

        this.viewManager.setView(Route.ADD_RECIPE);
        break;
      case ADD:
        this.addRecipe(recipe);
        currentSearch = getRecipes().values().stream().toList();
        view.render(currentSearch);
        this.viewManager.setView(Route.COOKBOOK);
        break;
      case REMOVE:
        recipeRegister.removeRecipe(recipe);
        currentSearch = getRecipes().values().stream().toList();
        view.render(currentSearch);
        viewManager.setView(Route.COOKBOOK);
        break;
      default:
        break;
    }

  }

  /**
   * Updates the observer with the button that was pressed. If the button is 'buttonEnum.ADD', it
   * opens the AddRecipeView for the user to add a new Recipe.
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

  /**
   * Searches for recipes in the recipeRegister based on the search string.
   * The search string is passed to the recipeRegister, and the currentSearch is set to the result
   * of the search. The view is then rendered with the currentSearch.
   *
   * @param search the search string to search for in the recipeRegister
   */
  public void searchRecipes(String search) {
    currentSearch = recipeRegister.searchRecipes(search);
    view.render(currentSearch);
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
      } catch (Exception e) {
        quantityInShoppingList = 0;
      }


      int totalQuantityAvailable = quantityInShelf + quantityInShoppingList;

      if (quantityNeeded > totalQuantityAvailable) {
        int quantityToAdd = quantityNeeded - totalQuantityAvailable;
        if (shoppingListGrocery != null) {
          shoppingListGrocery.setQuantity(shoppingListGrocery.getQuantity() + quantityToAdd);
        } else {
          shoppingListController.addGrocery(
                  new Grocery(groceryName, quantityToAdd, "g", groceryShelf, false));
        }
      }
    }

    shoppingListController.rerender();
  }

  /**
   * Adds a recipe to the recipeRegister and the database.
   *
   * @param recipe the recipe to add to the recipeRegister and the database.
   */
  private void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe cannot be null");
    }

    if (getRecipes().containsKey(recipe.getKey())) {
      throw new IllegalArgumentException("Recipe already exists");
    }

    String query = "INSERT INTO recipe (name, description, image) VALUES (?, ?, ?)";
    SQL.executeUpdate(query, recipe.getKey(), recipe.getDescription(), recipe.getImagePath());

    for (Grocery grocery : recipe.getRecipeGroceries().getRegister().values()) {
      String groceryQuery = "SELECT * FROM grocery WHERE name = ?";
      List<Map<String, Object>> groceryDataFromDataBase =
              SQL.executeQuery(groceryQuery, grocery.getKey());

      if (groceryDataFromDataBase.isEmpty()) {
        String insertGroceryQuery = "INSERT INTO grocery (name, unit) VALUES (?, ?)";
        SQL.executeUpdate(insertGroceryQuery, grocery.getKey(), grocery.getUnit());
      }

      String insertGroceryRecipeQuery = "INSERT INTO recipe_grocery "
              + "(recipe_id, grocery_name, quantity) VALUES (?, ?, ?)";
      SQL.executeUpdate(insertGroceryRecipeQuery, recipe.getKey(),
              grocery.getKey(), grocery.getQuantity());
    }

    for (String step : recipe.getRecipeSteps()) {
      String insertStepQuery = "INSERT INTO step (recipe_id, description) VALUES (?, ?)";
      SQL.executeUpdate(insertStepQuery, recipe.getKey(), step);
    }

    recipeRegister.addRecipe(recipe);
  }

  /**
   * Toggles the favorite status of a recipe.
   *
   * @param recipe the recipe to toggle the favorite status of.
   */
  private void toggleIsFavorite(Recipe recipe) {
    recipe.toggleIsFavorite();
    if (this.isLoggedIn()) {
      String name = recipe.getKey();

      //TODO: fix recipe to use id, not name
      String idQuery = "SELECT id FROM recipe WHERE name = ?";
      List<Map<String, Object>> idResult = SQL.executeQuery(idQuery, name);

      String id = idResult.getFirst().get("id").toString();

      String checkQuery = "SELECT * FROM recipe_favorite WHERE recipe_id = ? AND user_name = ?";
      List<Map<String, Object>> favorite = SQL.executeQuery(checkQuery, id, PantryPal.userName);

      if (favorite.isEmpty()) {
        String insertQuery = "INSERT INTO recipe_favorite (recipe_id, user_name) VALUES (?, ?)";
        SQL.executeUpdate(insertQuery, id, PantryPal.userName);
      } else {
        String deleteQuery = "DELETE FROM recipe_favorite WHERE recipe_id = ? AND user_name = ?";
        SQL.executeUpdate(deleteQuery, id, PantryPal.userName);
      }
    }
    view.render(currentSearch);
  }

  /**
   * Re-renders the view with the currentSearch.
   */
  public void rerender() {
    view.render(currentSearch);
  }
}
