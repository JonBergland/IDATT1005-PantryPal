package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.AddRecipeView;

import java.util.Objects;

/**
 * Controller class for the AddRecipeView. This class is responsible for handling the logic for the
 * AddRecipeView.
 */
public class AddRecipeController extends Controller implements Observer {
  private GroceryRegister groceryRegister;
  private StepRegister stepRegister;
  private static final String BUTTON_NOT_SUPPORTED = "Button not supported";

  /**
   * The view for the AddRecipeController
   */
  private AddRecipeView view;

  private final CookBookController cookBookController;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public AddRecipeController(ViewManager viewManager, CookBookController cookBookController) {
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
   * Sets the view to AddRecipeView with an already existing recipe to edit
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
   * Updates the observer with the button that was pressed and the object affected
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that was pressed
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
      case null, default -> throw new IllegalArgumentException("Object is not of type Recipe, Grocery or String");
    }
  }

  /**
   * Updates the observer with the button that was pressed
   *
   * @param buttonEnum the button that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public void rerender() {
    view.render(groceryRegister, stepRegister);
  }
}
