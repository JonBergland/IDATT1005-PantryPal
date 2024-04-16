package stud.ntnu.idatt1005.pantrypal.registers;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of steps in a recipe.
 * Contains methods for adding and removing steps.
 * Goal: To represent a list of steps in a recipe.
 *
 */
public class StepRegister {
  /**
   * A list of steps in a recipe.
   */
  List<String> steps;

  /**
   * Constructor for Steps.
   * Initializes the list of steps.
   */
  public StepRegister() {
    steps = new ArrayList<>();
  }

  /**
   * Returns the list of steps.
   *
   * @return steps The list of steps.
   *
   */
  public List<String> getSteps() {
    return steps;
  }

  /**
   * Adds a step to the list of steps.
   *
   * @param step The step to be added to the steps list.
   */
  public void addStep(String step) throws IllegalArgumentException {
    if (step == null || step.isEmpty()) {
      throw new IllegalArgumentException("Step cannot be empty.");
    }
    steps.add(step);
  }

  /**
   * Removes a step from the list of steps.
   *
   * @param index The index of the step to be removed from the steps list.
   */
  public void removeStep(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= steps.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds must be between 0 and "
          + (steps.size() - 1) + ".");
    }
    steps.remove(steps.get(index));
  }

  /**
   * Removes a step from the list of steps.
   *
   * @param step The step to be removed from the steps list.
   */
  public void removeStep(String step) {
    if (step == null || step.isEmpty()) {
      throw new IllegalArgumentException("Step cannot be empty.");
    }
    steps.remove(step);
  }
}
