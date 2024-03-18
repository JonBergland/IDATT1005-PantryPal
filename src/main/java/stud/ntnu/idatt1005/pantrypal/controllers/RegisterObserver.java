package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Model;
import stud.ntnu.idatt1005.pantrypal.registers.Register;
import stud.ntnu.idatt1005.pantrypal.views.ShoppingListView;

public class RegisterObserver implements Observer {
  private final ShoppingListView view;
  private Register register;

  public RegisterObserver(Register register, ShoppingListView view) {
    this.register = register;
    this.view = view;
  }

  public Register getRegister() {
    return register;
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
    if (object instanceof Model) {
      model = (Model) object;
    } else {
      throw new IllegalArgumentException("Object is not of type Model");
    }
    switch (buttonEnum) {
      case ADD:
        register.addItem(model);
        view.render();
        break;
      case REMOVE:
        register.removeItem(model);
        view.render();
        break;
      default:
        System.out.println("Button not supported by class");
        break;
    }
  }
}
