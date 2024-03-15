package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Model;
import stud.ntnu.idatt1005.pantrypal.registers.Register;

public class RegisterObserver implements Observer{
  Register register;
    public RegisterObserver(Register register) {
      this.register = register;
    }

  /**
   * Updates the observer
   *
   * @param buttonEnum the button that was pressed
   * @param object     the object that was pressed
   */
  @Override
  public void update(ButtonEnum buttonEnum, Object object) {
    Model model;
    if (object.getClass().isInstance(Model.class)) {
      model = (Model) object;
    } else {
      throw new IllegalArgumentException("Object is not of type Model");
    }
    switch (buttonEnum) {
      case ADD:
        register.addItem(model);
        break;
      case REMOVE:
        register.removeItem(model);
        break;
      default:
        System.out.println("Button not supported by class");
        break;
    }
  }
}
