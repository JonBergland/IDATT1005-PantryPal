package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.HomeView;

public class HomeController extends Controller {

  private final HomeView view;

  public HomeController(ViewManager viewManager) {
    super(viewManager);
    this.view = new HomeView(this);
    this.viewManager.addView(Route.HOME, this.view);
  }
}
