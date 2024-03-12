package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.Controller;

/**
 * Interface for the observable role in the observer pattern
 */
public interface Observable {

  /**
   * Adds an observer to the observable
   * @param controller the observer to be added
   */
  void addObserver(Controller controller);

  /**
   * Removes an observer from the observable
   * @param controller the observer to be removed
   */
  void removeObserver(Controller controller);

}
