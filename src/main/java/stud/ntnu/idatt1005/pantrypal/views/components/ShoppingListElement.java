package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;

public class ShoppingListElement {
  private Grocery grocery;
  private HBox groceryInfo;

  public ShoppingListElement(Grocery grocery) {

    StyledCheckBox checkBox = new StyledCheckBox("", false);
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

    StyledButton deleteButton = new StyledButton("Delete", StyledButton.Variant.DANGER, StyledButton.Size.MEDIUM);

    this.groceryInfo = new HBox(checkBox, stackGroceryName, stackGroceryCategory, stackGroceryAmount, deleteButton);


    this.grocery = grocery;
  }

  public HBox getGroceryInfo() {
    return groceryInfo;
  }


}
