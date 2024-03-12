package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.Observer;

/**
 * Interface for the observable role in the observer pattern
 */
public interface Observable {

  /**
   * Adds an observer to the observable
   * @param observer the observer to be added
   */
  void addObserver(Observer observer);

  /**
   * Removes an observer from the observable
   * @param observer the observer to be removed
   */
  void removeObserver(Observer observer);

}
