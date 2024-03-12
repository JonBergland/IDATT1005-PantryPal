package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * A class that extends View and creates a view for the pantry.
 * The goal is to provide a dedicated view for users to view their pantry.
 * This class is associated with an PantryController to handle the logic
 * and actions related to the pantry.
 */
public class PantryView extends View {

  /**
   * The controller responsible for managing the logic and
   * actions associated with the pantry functionality.
   */
  private final PantryController controller;

  /**
   * Constructor for PantryView.
   *
   * @param controller The controller for the view.
   */
  public PantryView(PantryController controller) {
    super(controller, Route.PANTRY);
    this.controller = controller;
  }
}
