package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.LogInView;

public class LogInController extends Controller {

  private final LogInView view;

  public LogInController(ViewManager viewManager) {
    super(viewManager);
    this.view = new LogInView(this);
    this.viewManager.addView(Route.LOGIN, this.view);
  }
}
