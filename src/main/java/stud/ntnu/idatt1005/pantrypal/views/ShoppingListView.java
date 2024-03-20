package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


import javafx.scene.paint.Color;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.views.components.AddShoppingListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.ShoppingListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;


import static javafx.stage.Screen.getPrimary;

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
   * @param controller The controller for the view. This controller is responsible for handling
   *                   the logic and actions associated with the shopping list functionality.
   */
  public ShoppingListView(ShoppingListController controller) {
    super(controller, Route.SHOPPING_LIST, "/styles/shopping-list.css");
    this.controller = controller;
    render();
  }

  /**
   * Renders the shopping list view. This method creates the overarching VBox, scroll pane, and
   * VBox to hold the shopping list elements. It then adds the shopping list elements to the VBox,
   * and finally adds the add shopping list element to the VBox.
   */
  public void render() {
    // Create the overarching VBox
    VBox shoppingListBox = new VBox();
    shoppingListBox.setMaxWidth(getPrimary().getVisualBounds().getWidth() * 0.5);
    shoppingListBox.setMaxHeight(getPrimary().getVisualBounds().getHeight() * 0.8);
    shoppingListBox.setAlignment(Pos.CENTER);
    shoppingListBox.setBackground(Background.fill(Color.WHITE));
    shoppingListBox.getStyleClass().add("shopping-list-box");

    // Create a title for the shopping list and a button for adding to the pantry
    HBox titleBox = new HBox();
    titleBox.setAlignment(Pos.CENTER);
    titleBox.getStyleClass().add("shopping-list-title");

    Text shoppingListTitle = new Text("Shopping List");
    shoppingListTitle.setFont(FontPalette.SHOPPING_LIST);

    StyledButton addToPantry = new StyledButton("Add to pantry", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

    Pane spacerLeft = new Pane();
    Pane spacerRight = new Pane();
    Pane spacerLeftCenter = new Pane();
    HBox.setHgrow(spacerLeft, Priority.ALWAYS);
    HBox.setHgrow(spacerRight, Priority.ALWAYS);
    HBox.setHgrow(spacerLeftCenter, Priority.ALWAYS);

    titleBox.getChildren().addAll(spacerLeft, spacerLeftCenter, shoppingListTitle, spacerRight, addToPantry);

    shoppingListBox.getChildren().add(titleBox);

    // Create the scroll pane to hold the shopping list
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMinHeight(getPrimary().getVisualBounds().getHeight() * 0.7);
    scrollPane.setPadding(new Insets(10, 0, 0, 0));
    scrollPane.setBackground(Background.fill(Color.WHITE));

    // Create the VBox to hold the shopping list elements
    VBox shoppingList = new VBox();
    scrollPane.setContent(shoppingList);
    shoppingList.setBackground(Background.fill(Color.WHITE));

    // render the scene
    for (Grocery grocery : controller.getRegister().getRegister().values()) {
      ShoppingListElement element = new ShoppingListElement(grocery);
      element.addObserver(controller);
      shoppingList.getChildren().add(element.getPane());
    }
    scrollPane.setContent(shoppingList);
    shoppingListBox.getChildren().add(scrollPane);

    // Add the add shopping list element to the VBox
    AddShoppingListElement addShoppingListElement = new AddShoppingListElement();
    addShoppingListElement.addObserver(controller);
    addShoppingListElement.setMaxHeight(50);
    addShoppingListElement.setAlignment(Pos.BOTTOM_CENTER);
    shoppingListBox.getChildren().add(addShoppingListElement);
    VBox.setVgrow(addShoppingListElement, Priority.ALWAYS);

    shoppingListBox.setAlignment(Pos.CENTER);

    getBorderPane().setCenter(shoppingListBox);
  }
}
