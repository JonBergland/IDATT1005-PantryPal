package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.LogOutView;

public class LogOutController extends Controller{
  private final LogOutView view;
  public LogOutController(ViewManager viewManager) {
    super(viewManager);
    this.view = new LogOutView(this);
    this.viewManager.addView(Route.LOGOUT, this.view);
  }
}
