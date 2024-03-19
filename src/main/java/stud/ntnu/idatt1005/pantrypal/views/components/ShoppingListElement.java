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
 * Class representing a shopping list element
 */
public class ShoppingListElement extends GroceryListElement {
  /**
   * The {@link BorderPane} with the shopping list element
   */
  private final BorderPane groceryInfo;

  /**
   * Constructor for the shopping list element
   * @param grocery The grocery to be displayed
   */
  public ShoppingListElement(Grocery grocery) {
    super(grocery);
    Objects.requireNonNull(this.getClass().getResource("/styles/components.css")).toExternalForm();

    // Make the checkbox
    StackPane checkBox = new StackPane(new StyledCheckBox("", false));
    StyledCheckBox centerCheckBox = new StyledCheckBox("", false);
    centerCheckBox.setAlignment(Pos.CENTER);
    checkBox.getChildren().add(centerCheckBox);

    HBox textBox = createTextBox(grocery.getKey(), grocery.getCategory(), grocery.getQuantity() + "stk");

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
   * Retrieves the shopping list element
   * @return the shopping list element
   */
  public Pane getPane() {
    return groceryInfo;
  }

}
