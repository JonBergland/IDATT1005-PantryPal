package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class PantryView extends View {

  private final PantryController controller;

  public PantryView(PantryController controller) {
    super(Route.PANTRY);
    this.controller = controller;
  }
}
