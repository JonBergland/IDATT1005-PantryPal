package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;

/**
 * Class representing a shopping list element
 */
public class ShoppingListElement {
  /**
   * The {@link BorderPane} with the shopping list element
   */
  private final BorderPane groceryInfo;

  /**
   * Constructor for the shopping list element
   * @param grocery The grocery to be displayed
   */
  public ShoppingListElement(Grocery grocery) {

    // Make the checkbox
    StackPane checkBox = new StackPane(new StyledCheckBox("", false));
    StyledCheckBox centerCheckBox = new StyledCheckBox("", false);
    centerCheckBox.setAlignment(Pos.CENTER);
    checkBox.getChildren().add(centerCheckBox);

    // Make the text
    Text groceryName = new Text(grocery.getName());
    Text groceryCategory = new Text(grocery.getCategory());
    Text groceryAmount = new Text(grocery.getQuantity() + "stk");

    groceryName.setFont(FontPalette.TEXT);
    groceryCategory.setFont(FontPalette.TEXT);
    groceryAmount.setFont(FontPalette.TEXT);

    StackPane stackGroceryName = new StackPane(groceryName);
    stackGroceryName.setPadding(new Insets(0, 0, 0, 10));

    StackPane stackGroceryCategory = new StackPane(groceryCategory);
    stackGroceryCategory.setPadding(new Insets(0, 0, 0, 20));

    StackPane stackGroceryAmount = new StackPane(groceryAmount);
    stackGroceryAmount.setPadding(new Insets(0, 10, 0, 10));

    // Add the text to a HBox
    HBox textBox = new HBox(stackGroceryName, stackGroceryCategory, stackGroceryAmount);
    textBox.setSpacing(25);
    textBox.setAlignment(Pos.CENTER);

    // Make the delete button
    StyledButton deleteButton = new StyledButton("Delete", StyledButton.Variant.DANGER, StyledButton.Size.MEDIUM);

    // Add the checkbox, text and delete button to a BorderPane
    this.groceryInfo = new BorderPane();
    this.groceryInfo.setLeft(checkBox);
    this.groceryInfo.setCenter(textBox);
    this.groceryInfo.setRight(deleteButton);
  }

  /**
   * Retrieves the shopping list element
   * @return the shopping list element
   */
  public BorderPane getGroceryInfo() {
    return groceryInfo;
  }


}
