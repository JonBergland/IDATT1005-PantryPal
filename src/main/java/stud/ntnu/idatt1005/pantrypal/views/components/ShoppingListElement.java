package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;

import java.util.Objects;

/**
 * Class representing a shopping list element. This class extends the GroceryListElement class
 * and provides a visual representation of a grocery item in the shopping list.
 * It includes a checkbox, text information about the grocery item, and a delete button.
 */
public class ShoppingListElement extends GroceryListElement {

  /**
   * The {@link BorderPane} containing the visual elements of the shopping list item.
   * This includes a checkbox, text information about the grocery item, and a delete button.
   */
  private final BorderPane groceryInfo;

  /**
   * Constructor for the shopping list element. It initializes the visual elements
   * and sets up the necessary event handlers.
   *
   * @param grocery The grocery item to be displayed in the shopping list element.
   */
  public ShoppingListElement(Grocery grocery) {
    super(grocery);

    // Make the checkbox
    StackPane checkBox = new StackPane();
    StyledCheckBox centerCheckBox = new StyledCheckBox("", false);
    centerCheckBox.setAlignment(Pos.CENTER);
    checkBox.getChildren().add(centerCheckBox);

    HBox textBox = createTextBox(grocery.getKey(), grocery.getShelf(), grocery.getQuantity() + "stk");
    textBox.setAlignment(Pos.CENTER);

    // Make the delete button
    StyledButton deleteButton = createButton("Delete", StyledButton.Variant.DANGER, StyledButton.Size.MEDIUM, ButtonEnum.REMOVE);

    // Add the checkbox, text and delete button to a BorderPane
    this.groceryInfo = new BorderPane();
    this.groceryInfo.setLeft(checkBox);
    this.groceryInfo.setCenter(textBox);
    this.groceryInfo.setRight(deleteButton);
    this.groceryInfo.getStyleClass().add("shopping-list-element");

  }

  /**
   * Retrieves the visual representation of the shopping list element.
   *
   * @return the {@link Pane} containing the visual elements of the shopping list item.
   */
  public Pane getPane() {
    return groceryInfo;
  }

}
