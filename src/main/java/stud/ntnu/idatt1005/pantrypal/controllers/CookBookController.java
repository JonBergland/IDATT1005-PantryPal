package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
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

    addPlaceholderRecipes();
    this.currentSearch = getRecipes().values().stream().toList();

    this.view = new CookbookView(this);
    this.view.addObserver(this);
    this.viewManager.addView(Route.COOKBOOK, view);
  }

  /**
   * Returns the LinkedHashMap in the recipeRegister.
   *
   * @return the LinkedHashMap in the recipeRegister.
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
        if (getRecipes().containsKey(recipe.getKey())) {
          recipeRegister.removeRecipe(recipe);
        }
        recipeRegister.addRecipe(recipe);
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
              new Grocery(groceryName, quantityToAdd, groceryShelf, false));
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
    view.render(currentSearch);
  }

  /**
   * Adds placeholder recipes to the recipe register.
   */
  private void addPlaceholderRecipes() {
    String cupboard = "Cupboard";
    // Recipe 1
    GroceryRegister groceries1 = new GroceryRegister();
    groceries1.addGrocery(new Grocery("Tomato", 1, cupboard, false));
    groceries1.addGrocery(new Grocery("Onion", 1, cupboard, false));
    groceries1.addGrocery(new Grocery("Garlic", 1, cupboard, false));

    StepRegister steps1 = new StepRegister();
    steps1.addStep("Cut groceries");
    steps1.addStep("Cook groceries");
    steps1.addStep("Eat groceries");

    Recipe recipe1 = new Recipe("Tomato soup", "", groceries1, steps1, null, false);
    recipeRegister.addRecipe(recipe1);

    // Recipe 2

    GroceryRegister groceries2 = new GroceryRegister();
    groceries2.addGrocery(new Grocery("Milk", 1, "Fridge", false));
    groceries2.addGrocery(new Grocery("Porridge rice", 1, cupboard, false));
    groceries2.addGrocery(new Grocery("Sugar", 1, cupboard, false));
    groceries2.addGrocery(new Grocery("Cinnamon", 1, cupboard, false));

    StepRegister steps2 = new StepRegister();
    steps2.addStep("Boil milk");
    steps2.addStep("Add porridge rice");
    steps2.addStep("Add sugar and cinnamon");

    Recipe recipe2 = new Recipe("Rice porridge", "", groceries2, steps2, null, false);
    recipeRegister.addRecipe(recipe2);

    // Recipe 3

    GroceryRegister groceries3 = new GroceryRegister();
    groceries3.addGrocery(new Grocery("Pasta", 1, cupboard, false));
    groceries3.addGrocery(new Grocery("Tomato sauce", 1, cupboard, false));
    groceries3.addGrocery(new Grocery("Cheese", 1, "Fridge", false));

    StepRegister steps3 = new StepRegister();
    steps3.addStep("Boil pasta");
    steps3.addStep("Add tomato sauce");
    steps3.addStep("Add cheese");

    Recipe recipe3 = new Recipe("Pasta", "", groceries3, steps3, null, false);
    recipeRegister.addRecipe(recipe3);
  }
}
