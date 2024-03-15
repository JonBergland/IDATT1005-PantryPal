package stud.ntnu.idatt1005.pantrypal.views;

import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.views.components.AddShelfButton;
import stud.ntnu.idatt1005.pantrypal.views.components.ShelfComp;


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
  private  final VBox root;
  private final AddShelfButton addShelfButton;

  /**
   * Constructor for PantryView.
   *
   * @param controller The controller for the view.
   */
  public PantryView(PantryController controller, ShelfComp shelfComp) {
    super(controller, Route.PANTRY, "/styles/pantry.css");
    this.controller = controller;

    root = new VBox();
    addShelfButton = new AddShelfButton(this);
    root.getChildren().addAll(addShelfButton, shelfComp);
    setRoot(root);

    view(getBorderPane());
  }

  public void addShelf(ShelfComp shelfComp) {
    root.getChildren().add(shelfComp);
  }
}
