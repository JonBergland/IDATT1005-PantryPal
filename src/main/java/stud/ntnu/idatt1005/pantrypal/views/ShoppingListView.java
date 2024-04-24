package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.utils.SoundPlayer;
import stud.ntnu.idatt1005.pantrypal.views.components.AddGroceryListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.GroceryListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

/**
 * A class that extends View and creates a view for the shopping list.
 * The goal is to provide a dedicated view for users to view their shopping list.
 */
public class ShoppingListView extends View {

  /**
   * Constructor for ShoppingListView.
   *
   * @param controller The controller for the view. This controller is passed to the
   *                   super class View.
   */
  public ShoppingListView(ShoppingListController controller) {
    super(controller, Route.SHOPPING_LIST, "/styles/shopping-list.css");
  }

  /**
   * Renders the shopping list view. This method creates the overarching VBox, scroll pane, and
   * VBox to hold the shopping list elements. It then adds the shopping list elements to the VBox,
   * and finally adds the add shopping list element to the VBox.
   */
  public void render(GroceryRegister register) {
    // Create the overarching VBox
    VBox shoppingListBox = createShoppingListBox();

    // Create a title for the shopping list
    HBox titleBox = createTitleBox();

    // Create the scroll pane to hold the shopping list
    ScrollPane scrollPane = createScrollPane();

    // Create the VBox to hold the shopping list elements
    VBox shoppingList = createShoppingList(register);
    scrollPane.setContent(shoppingList);

    // Add a button to add to pantry
    StyledButton addToPantry = createAddToPantryButton();

    // Create the add grocery list element
    AddGroceryListElement addGroceryListElement = createAddGroceryListElement();

    shoppingListBox.getChildren().addAll(titleBox, scrollPane, addToPantry, addGroceryListElement);
    shoppingListBox.setAlignment(Pos.CENTER);

    getBorderPane().setCenter(shoppingListBox);
  }

  /**
   * Creates an overarching VBox to hold the shopping list elements.
   * The VBox is styled with the shopping-list-box class.
   *
   * @return the VBox to hold the shopping list elements
   */
  private VBox createShoppingListBox() {
    VBox shoppingListBox = new VBox();
    shoppingListBox.setMaxWidth(getPrimary().getVisualBounds().getWidth() * 0.5);
    shoppingListBox.setMaxHeight(getPrimary().getVisualBounds().getHeight() * 0.70);
    shoppingListBox.setAlignment(Pos.CENTER);
    shoppingListBox.getStyleClass().add("shopping-list-box");
    return shoppingListBox;
  }

  /**
   * Creates a HBox to hold the title for the shopping list.
   *
   * @return the HBox to hold the title for the shopping list
   */
  private HBox createTitleBox() {
    HBox titleBox = new HBox();
    titleBox.setAlignment(Pos.CENTER);
    titleBox.getStyleClass().add("shopping-list-title");

    Text shoppingListTitle = new Text("Shopping List");
    shoppingListTitle.setFont(FontPalette.SHOPPING_LIST);

    Pane spacerLeft = new Pane();
    Pane spacerRight = new Pane();

    spacerLeft.setMinWidth(10);
    HBox.setHgrow(spacerRight, Priority.ALWAYS);

    titleBox.getChildren().addAll(spacerLeft, shoppingListTitle, spacerRight);
    return titleBox;
  }

  /**
   * Creates a scroll pane to hold the shopping list elements.
   *
   * @return the scroll pane to hold the shopping list.
   */
  private ScrollPane createScrollPane() {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMinHeight(getPrimary().getVisualBounds().getHeight() * 0.60);
    scrollPane.setPadding(new Insets(10, 0, 0, 0));
    scrollPane.setBackground(Background.fill(Color.WHITE));
    return scrollPane;
  }

  /**
   * Creates a VBox to hold the shopping list elements.
   *
   * @param register The register containing the groceries.
   * @return the VBox to hold the shopping list elements
   */
  private VBox createShoppingList(GroceryRegister register) {
    VBox shoppingList = new VBox();
    for (Grocery grocery : register.getRegister().values()) {
      GroceryListElement element = new GroceryListElement.GroceryListElementBuilder(grocery)
          .checkBox()
          .text(grocery.getName())
          .text(grocery.getShelf())
          .quantity()
          .text(grocery.getUnit())
          .build();

      for (Observer observer : observers) {
        element.addObserver(observer);
      }
      shoppingList.getChildren().add(element.getPane());
    }
    return shoppingList;
  }

  /**
   * Creates a button to add groceries to the pantry.
   * The button is styled with the solid variant and medium size.
   * The button has an action that notifies the observers of the view.
   *
   * @return the button to add groceries to the pantry
   */
  private StyledButton createAddToPantryButton() {
    StyledButton addToPantry = new StyledButton(
        "Add to pantry", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);
    addToPantry.setOnAction(e -> {
      notifyObservers(ButtonEnum.ADD_TO_PANTRY);
      SoundPlayer.playSound(SoundPlayer.Sound.DEFAULT);
    });
    addToPantry.setMinWidth(getPrimary().getVisualBounds().getWidth() * 0.5);
    return addToPantry;
  }

  /**
   * Creates an AddGroceryListElement to add groceries to the shopping list.
   * The AddGroceryListElement contains text fields for the name, shelf,
   * and quantity of the grocery, in addition to an add button that adds the grocery to the list.
   *
   * @return the AddGroceryListElement to add groceries to the shopping list
   */
  private AddGroceryListElement createAddGroceryListElement() {
    AddGroceryListElement addGroceryListElement = new AddGroceryListElement("");
    for (Observer observer : observers) {
      addGroceryListElement.addObserver(observer);
    }

    addGroceryListElement.setMaxHeight(50);
    addGroceryListElement.setAlignment(Pos.BOTTOM_CENTER);
    VBox.setVgrow(addGroceryListElement, Priority.ALWAYS);
    return addGroceryListElement;
  }
}
