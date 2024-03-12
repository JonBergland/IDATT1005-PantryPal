package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

public class CookBookView extends View {

  private final CookBookController controller;

  public CookBookView(CookBookController controller) {
    super(Route.COOKBOOK);
    this.controller = controller;
  }
}
