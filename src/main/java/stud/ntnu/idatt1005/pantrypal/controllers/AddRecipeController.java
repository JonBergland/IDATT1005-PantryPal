package stud.ntnu.idatt1005.pantrypal.controllers;

import java.util.Objects;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.AddRecipeView;

/**
 * Controller class for the AddRecipeView. This class is responsible for handling the logic for the
 * AddRecipeView.
 */
public class AddRecipeController extends Controller implements Observer {
  private GroceryRegister groceryRegister;
  private StepRegister stepRegister;
  private static final String BUTTON_NOT_SUPPORTED = "Button not supported";

  /**
   * The view for the AddRecipeController.
   */
  private AddRecipeView view;

  private final CookbookController cookBookController;

  /**
   * Constructor that takes in a ViewManager and a CookBookController. The viewManager
   * is sent to the superclass Constructor. the AddRecipeView is created and rendered with
   * the controller. The view is added to the viewManager.
   *
   * @param viewManager the ViewManager for the application
   * @param cookBookController the CookBookController for the application
   */
  public AddRecipeController(ViewManager viewManager, CookbookController cookBookController) {
    super(viewManager);
    this.groceryRegister = new GroceryRegister();
    this.stepRegister = new StepRegister();
    this.view = new AddRecipeView(this);
    this.view.addObserver(this);
    this.view.render(groceryRegister, stepRegister);
    this.viewManager.addView(Route.ADD_RECIPE, this.view);

    this.cookBookController = cookBookController;

  }

  /**
   * Changes the view to a new AddRecipeView() with an already existing recipe to edit.
   *
   * @param recipe the recipe to edit
   */
  public void setRecipeToAddRecipeView(Recipe recipe) {
    this.view = new AddRecipeView(this, recipe);
    this.viewManager.addView(Route.ADD_RECIPE, this.view);
    this.view.addObserver(this);
    StepRegister recipeStepRegister = new StepRegister();
    recipe.getRecipeSteps().forEach(recipeStepRegister::addStep);
    this.groceryRegister = recipe.getRecipeGroceries();
    this.stepRegister = recipeStepRegister;
    this.view.render(this.groceryRegister, this.stepRegister);
  }

  /**
   * Updates the observer based on the type of button pressed, and object associated
   * object.
   * If the object is a Recipe it will be added to the Cookbook.
   * If the object is a Grocery it will be added/removed to/from the GroceryRegister,
   * and the view will be re-rendered.
   * If the object is a String it will be added/removed to/from the StepRegister,
   * and the view will be re-rendered.
   *
   *
   * @param buttonEnum the button type that was pressed
   * @param object     the object that is associated with the button
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    switch (object) {
      case Recipe recipe -> {
        if (Objects.requireNonNull(buttonEnum) == ButtonEnum.ADD) {
          cookBookController.update(ButtonEnum.ADD, recipe);
        } else {
          throw new UnsupportedOperationException(BUTTON_NOT_SUPPORTED);
        }
      }
      case Grocery grocery -> {
        switch (buttonEnum) {
          case ADD:
            groceryRegister.addGrocery(grocery);
            rerender();
            break;
          case REMOVE:
            groceryRegister.removeGrocery(grocery);
            rerender();
            break;
          default:
            throw new UnsupportedOperationException(BUTTON_NOT_SUPPORTED);
        }
      }
      case String string -> {
        switch (buttonEnum) {
          case ADD:
            stepRegister.addStep(string);
            rerender();
            break;
          case REMOVE:
            stepRegister.removeStep(string);
            rerender();
            break;
          default:
            throw new UnsupportedOperationException(BUTTON_NOT_SUPPORTED);
        }
      }
      case null, default -> throw new IllegalArgumentException(
              "Object is not of type Recipe, Grocery or String");
    }
  }

  /**
   * Updates the observer with the button that was pressed.
   *
   * @param buttonEnum the button that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Re-renders the view with the current groceryRegister and stepRegister.
   */
  public void rerender() {
    view.render(groceryRegister, stepRegister);
  }
}
