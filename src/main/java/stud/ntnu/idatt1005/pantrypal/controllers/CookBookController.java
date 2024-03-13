package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.CookbookView;

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
  private HashMap<String, Recipe> recipes;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public CookBookController(ViewManager viewManager) {
    super(viewManager);
    this.recipes = new HashMap<>();

    for (int i = 1; i <= 18; i++) {
      Recipe recipe = new Recipe("Recipe " + i, null, null);
      addRecipe(recipe);
    }
    this.view = new CookbookView(this);
    this.viewManager.addView(Route.COOKBOOK, this.view);
  }

  public Map<String, Recipe> getRecipes() {
    return this.recipes;
  }

  public void addRecipe(Recipe recipe) {
    this.recipes.put(recipe.getKey(), recipe);
  }

}
