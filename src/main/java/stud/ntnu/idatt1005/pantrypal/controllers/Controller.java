package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;

public class Controller {

  final ViewManager viewManager;

  public Controller(ViewManager viewManager) {
    this.viewManager = viewManager;
  }

  public void onNavLinkPress(Route route){
    viewManager.setView(route);
  }
}
