package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.CookBookView;

public class CookBookController extends Controller {

  private final CookBookView view;

  public CookBookController(ViewManager viewManager) {
    super(viewManager);
    this.view = new CookBookView(this);
    this.viewManager.addView(Route.COOKBOOK, this.view);
  }
}
