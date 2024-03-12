package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.utils.ButtonEnum;

/**
 * Interface for the observer role in the observer pattern
 */
public interface Controller {
    void update(ButtonEnum buttonEnum, String buttonData);

    void update(ButtonEnum buttonEnum, Object object);
}
