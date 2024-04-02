package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;

/**
 * Interface for the observer role in the observer pattern
 */
public interface Observer {

  /**
   * Updates the observer with the button that was pressed and the object affected
   * @param buttonEnum the button that was pressed
   * @param object the object that was pressed
   */
  void update(ButtonEnum buttonEnum, Object object);

  /**
   * Updates the observer with the button that was pressed
   * @param buttonEnum the button that was pressed
   */
  void update(ButtonEnum buttonEnum);
}