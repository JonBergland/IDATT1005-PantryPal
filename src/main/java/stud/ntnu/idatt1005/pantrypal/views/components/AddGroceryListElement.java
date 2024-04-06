package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddGroceryListElement extends HBox implements Observable {
  private static final List<Observer> observers = new ArrayList<>();

  private AddGroceryListElement() {}

  @Override
  public void addObserver(Observer observer) {
    if (observer != null) {
      observers.add(observer);
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  @Override
  public void removeObserver(Observer observer) {
    if (observer != null) {
      observers.remove(observer);
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  public static class AddGroceryListElementBuilder {
    private StyledTextField name;
    private StyledTextField shelf;
    private StyledTextField quantity;
    private StyledButton add;
    public AddGroceryListElementBuilder() {}

    public AddGroceryListElementBuilder name() {
      StyledTextField name = new StyledTextField("Name");
      this.name = name;
      return this;
    }

    public AddGroceryListElementBuilder shelfTextField() {
      StyledTextField shelf = new StyledTextField("Shelf");
      this.shelf = shelf;
      return this;
    }

    public AddGroceryListElementBuilder shelfName(String shelfName) {
      StyledTextField shelf = new StyledTextField("Shelf");
      shelf.setText(shelfName);
      this.shelf = shelf;
      return this;
    }

    public AddGroceryListElementBuilder quantity() {
      StyledTextField quantity = new StyledTextField("Quantity");
      this.quantity = quantity;
      return this;
    }

    public AddGroceryListElementBuilder addButton() {
      StyledButton add = getStyledButton(name, shelf, quantity);
      this.add = add;
      return this;
    }

    public AddGroceryListElement build() throws IllegalArgumentException {
      if (name == null || shelf == null || quantity == null || add == null) {
        throw new IllegalArgumentException("Name, shelf, quantity and addButton cannot be null");
      }
      AddGroceryListElement addGroceryListElement = new AddGroceryListElement();
      addGroceryListElement.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/pantry.css")).toExternalForm());
      addGroceryListElement.setAlignment(Pos.CENTER);
      NodeUtils.addClasses(addGroceryListElement, "add-grocery-container");

      if (shelf.getText().isEmpty()) {
        addGroceryListElement.getChildren().addAll(
            styleText(name, 200),
            styleText(shelf, 200),
            styleText(quantity, 100),
            add);
      } else {
        addGroceryListElement.getChildren().addAll(
            styleText(name, 100),
            styleText(quantity, 100),
            add);
      }

      return addGroceryListElement;
    }

    /**
     * Creates a styled addButton with the given text fields and returns it.
     * The addButton is styled with the add-grocery-addButton class.
     *
     * @param name the name text field
     * @param shelf the shelf text field
     * @param quantity the quantity text field
     * @return a styled addButton
     */
    private StyledButton getStyledButton(StyledTextField name, StyledTextField shelf, StyledTextField quantity) {
      StyledButton add = new StyledButton("Add", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

      // Set the action for the add addButton
      add.setOnAction(e -> {
        if (!name.getText().isEmpty()) {
          if (quantity.getText().isEmpty()) {
            quantity.setText("1");
          }
          if (shelf.getText().isEmpty()) {
            shelf.setText("Unassigned");
          }
          notifyObservers(ButtonEnum.ADD_TO_SHOPPING_LIST, name.getText(), Integer.parseInt(quantity.getText()), shelf.getText(), false);
          name.clear();
          shelf.clear();
          quantity.clear();
        }
      });
      return add;
    }

    /**
     * Styles the text field given as parameter and returns
     * a StackPane with the text field.
     * The text field is styled with the add-grocery-textfield class
     * and has a max width of the given width.
     *
     * @param textField the text field to be styled
     * @param width the width of the text field
     * @return A Stack pane with the styled text field
     */
    private StackPane styleText(StyledTextField textField, int width) {
      NodeUtils.addClasses(textField, "add-grocery-textfield");
      textField.setMaxWidth(width);

      StackPane stackPane = new StackPane(textField);
      stackPane.setAlignment(Pos.CENTER);

      return stackPane;
    }

    /**
     * Notifies the observers in the observer list with the given buttonEnum.
     *
     * @param buttonEnum the buttonEnum to be sent to the observers
     */
    private void notifyObservers(ButtonEnum buttonEnum, String name, int quantity, String shelf, boolean checked) {
      Grocery grocery = new Grocery(name, quantity, shelf, checked);
      List<Observer> observers = new ArrayList<>(AddGroceryListElement.observers);
      for (Observer observer : observers) {
        observer.update(buttonEnum, grocery);
      }
    }
  }
}
