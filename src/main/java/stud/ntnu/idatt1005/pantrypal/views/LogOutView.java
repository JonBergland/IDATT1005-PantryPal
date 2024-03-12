package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.LogOutController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class LogOutView extends View {

  private final LogOutController controller;

  public LogOutView(LogOutController controller) {
    super(controller, Route.LOGOUT);
    this.controller = controller;
  }
}
