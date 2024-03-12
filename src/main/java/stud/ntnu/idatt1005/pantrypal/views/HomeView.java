package stud.ntnu.idatt1005.pantrypal.views;

import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;

/**
 * This class represents the HomeView in the application. It extends the View class and sets the
 * scene for the stage. The HomeView is created with the HOME viewType, providing a central
 * view for the applications home screen.
 * This class is associated with an HomeController to handle the logic and actions related to the
 * home screen.
 */
public class HomeView extends View {

  /**
   * Constructor for HomeView.
   *
   * @param controller The controller for the view.
   */
  public HomeView(HomeController controller) {
    super(controller, Route.HOME);
  }
}