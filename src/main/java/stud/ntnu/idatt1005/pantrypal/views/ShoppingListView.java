package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.views.components.AddShoppingListElement;
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

  /**
   * Renders the shopping list view.
   */
  public void render() {
    // Create the overarching VBox
    VBox shoppingListBox = new VBox();
    shoppingListBox.setMaxWidth(610);
    shoppingListBox.setMaxHeight(500);
    shoppingListBox.setAlignment(Pos.CENTER);
    shoppingListBox.setBackground(Background.fill(Color.WHITE));
    shoppingListBox.getStyleClass().add("shopping-list-box");

    // Create the scroll pane to hold the shopping list
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMinHeight(450);
    scrollPane.setMaxWidth(590);
    scrollPane.setMaxHeight(500);
    scrollPane.setPadding(new Insets(10, 0, 0, 0));
    scrollPane.setBackground(Background.fill(Color.WHITE));

    // Create the VBox to hold the shopping list elements
    VBox shoppingList = new VBox();
    scrollPane.setContent(shoppingList);
    shoppingList.setBackground(Background.fill(Color.WHITE));

    // Add the shopping list elements to the VBox
    for (Grocery grocery : controller.getRegister().values()) {
      ShoppingListElement element = new ShoppingListElement(grocery);
      element.addObserver(controller);
      shoppingList.getChildren().add(element.getPane());
    }
    scrollPane.setContent(shoppingList);
    shoppingListBox.getChildren().add(scrollPane);

    // Add the add shopping list element to the VBox
    AddShoppingListElement addShoppingListElement = new AddShoppingListElement();
    addShoppingListElement.addObserver(controller);
    addShoppingListElement.setMaxWidth(600);
    addShoppingListElement.setAlignment(Pos.BOTTOM_CENTER);
    shoppingListBox.getChildren().add(addShoppingListElement);
    VBox.setVgrow(addShoppingListElement, javafx.scene.layout.Priority.ALWAYS);

    shoppingListBox.setAlignment(Pos.CENTER);

    getBorderPane().setCenter(shoppingListBox);
  }
}
