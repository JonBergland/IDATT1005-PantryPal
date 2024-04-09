package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.AddRecipeView;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the AddRecipeView. This class is responsible for handling the logic for the
 * AddRecipeView.
 */
public class AddRecipeController extends Controller implements Observer, Observable {
  private final List<Observer> observers = new ArrayList<>();
  private final GroceryRegister groceryRegister;
  private final StepRegister stepRegister;

  /**
   * The view for the AddRecipeController
   */
  private final AddRecipeView view;

  /**
   * Constructor that takes in a ViewManager and sets the view for the controller
   *
   * @param viewManager the ViewManager for the application
   */
  public AddRecipeController(ViewManager viewManager) {
    super(viewManager);
    this.groceryRegister = new GroceryRegister();
    this.stepRegister = new StepRegister();
    this.stepRegister.addStep("Step 1");
    this.view = new AddRecipeView(this);
    this.view.addObserver(this);
    this.view.render(groceryRegister, stepRegister);
    this.viewManager.addView(Route.ADD_RECIPE, this.view);


  }

  /**
   * Updates the observer with the button that was pressed and the object affected
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    if (object instanceof Recipe recipe) {
      switch (buttonEnum) {
        case ADD:
          notifyObservers(ButtonEnum.ADD, recipe);
          break;
      }
    } else if (object instanceof Grocery grocery) {
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
          System.out.println("Button not found");
          break;
      }
    } else if (object instanceof String string) {
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
          System.out.println("Button not found");
          break;
      }
    }
    else {
      throw new IllegalArgumentException("Object is not of type Recipe, Grocery or String");
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

  /**
   * Adds an observer to the list of observers for this view.
   *
   * @param observer The observer to be added.
   * @throws IllegalArgumentException If the observer is null.
   */
  @Override
  public void addObserver(Observer observer) throws IllegalArgumentException {
    if (observer != null) {
      if (!observers.contains(observer)) {
        observers.add(observer);
      }
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  /**
   * Removes an observer from the list of observers for this view.
   *
   * @param observer The observer to be removed.
   * @throws IllegalArgumentException If the observer is null.
   */
  @Override
  public void removeObserver(Observer observer) throws IllegalArgumentException {
    if (observer != null) {
      if (observers.contains(observer)) {
        observers.remove(observer);
      }
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  /**
   * Notifies all observers with the given ButtonEnum.
   *
   * @param buttonEnum The ButtonEnum to notify the observers with.
   */
  protected void notifyObservers(ButtonEnum buttonEnum, Recipe recipe) {
    List<Observer> observersCopy = new ArrayList<>(this.observers);
    for (Observer observer : observersCopy) {
      observer.update(buttonEnum, recipe);
    }
  }
}
