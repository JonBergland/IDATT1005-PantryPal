package stud.ntnu.idatt1005.pantrypal.controllers;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;
import stud.ntnu.idatt1005.pantrypal.views.PantryView;

public class PantryController extends Controller {

  private final PantryView view;

  public PantryController(ViewManager viewManager) {
    super(viewManager);
    this.view = new PantryView(this);
    this.viewManager.addView(Route.PANTRY, this.view);
  }
}
