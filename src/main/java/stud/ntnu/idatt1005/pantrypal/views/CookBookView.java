package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * A class that extends View and creates a view for the cookbook.
 * The goal is to provide a dedicated view for users to view their cookbook.
 * This class is associated with an CookBookController to handle the logic
 * and actions related to the cookbook.
 */
public class CookBookView extends View {

  /**
   * The controller responsible for managing the logic and
   * actions associated with the cookbook functionality.
   */
  private final CookBookController controller;

  /**
   * Constructor for CookBookView.
   *
   * @param controller The controller for the view.
   */
  public CookBookView(CookBookController controller) {
    super(controller, Route.COOKBOOK);
    this.controller = controller;
  }
}
