package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.utils.ButtonEnum;

/**
 * Interface for the observer role in the observer pattern
 */
public interface Observer {

  /**
   * Updates the observer
   * @param buttonEnum the button that was pressed
   * @param object the object that was pressed
   */
  void update(ButtonEnum buttonEnum, Object object);
}