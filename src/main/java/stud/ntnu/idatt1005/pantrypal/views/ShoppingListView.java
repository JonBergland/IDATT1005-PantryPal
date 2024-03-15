package stud.ntnu.idatt1005.pantrypal.views;

import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Model;
import stud.ntnu.idatt1005.pantrypal.views.components.ShoppingListElement;

/**
 * A class that extends View and creates a view for the shopping list.
 * The goal is to provide a dedicated view for users to view their shopping list.
 * This class is associated with an ShoppingListController to handle the logic
 * and actions related to the shopping list.
 */
public class ShoppingListView extends View {

  /**
   * The controller responsible for managing the logic and
   * actions associated with the shopping list functionality.
   */
  private final ShoppingListController controller;

  /**
   * Constructor for ShoppingListView.
   *
   * @param controller The controller for the view.
   */
  public ShoppingListView(ShoppingListController controller) {
    super(controller, Route.SHOPPING_LIST, "/styles/shopping-list.css");
    this.controller = controller;
    render();
  }

  public void render() {
    VBox vBox = new VBox();

    // render the scene
    for (Model m : controller.getRegister().getRegisterMap().values()) {
      Grocery g = (Grocery) m;
      ShoppingListElement element = new ShoppingListElement(g);
      element.addObserver(controller);
      vBox.getChildren().add(element.getPane());
    }
    getBorderPane().setCenter(vBox);
  }
}
