package stud.ntnu.idatt1005.pantrypal.views.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.views.Observable;


/**
 * A class for creating input fields and an add button.
 * These fields are used to create and add a grocery to a grocery list.
 * The class extends HBox and implements the Observable interface.
 */
public class AddGroceryListElement extends HBox implements Observable {
  private final List<Observer> observers = new ArrayList<>();
  private static final String OBSERVER_ERROR_MESSAGE = "Observer cannot be null";

  /**
   * Constructor for the AddGroceryListElement class.
   * Creates a text field for the name, shelf and quantity of the grocery.
   * Also creates an add button that adds the grocery to the list.
   *
   * @param shelfName the name of the shelf
   */
  public AddGroceryListElement(String shelfName) {
    StyledTextField name = new StyledTextField("Name");
    StyledTextField shelf = createShelfTextField(shelfName);
    StyledTextField quantity = new StyledTextField("Quantity");
    StyledButton addButton = getStyledButton(name, shelf, quantity);

    this.getStylesheets().add(Objects.requireNonNull(
        getClass().getResource("/styles/pantry.css")).toExternalForm());
    this.setAlignment(Pos.CENTER);
    NodeUtils.addClasses(this, "add-grocery-container");

    if (shelf.getText().isEmpty()) {
      this.getChildren().addAll(
          styleText(name, 200),
          styleText(shelf, 200),
          styleText(quantity, 100),
          addButton);
    } else {
      this.getChildren().addAll(
          styleText(name, 100),
          styleText(quantity, 100),
          addButton);
    }
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
  private StyledButton getStyledButton(StyledTextField name,
                                       StyledTextField shelf, StyledTextField quantity) {
    StyledButton addButton = new StyledButton(
        "Add", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

    // Set the action for the add addButton
    addButton.setOnAction(e -> {
      if (!name.getText().isEmpty()) {
        if (quantity.getText().isEmpty()) {
          quantity.setText("1");
        }
        if (shelf.getText().isEmpty()) {
          shelf.setText("Unassigned");
        } else {
          // Capitalize the first letter of the shelf
          String str = shelf.getText();
          shelf.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
        }
        notifyObservers(ButtonEnum.ADD, name.getText(), Integer.parseInt(quantity.getText()),
            shelf.getText());
        name.clear();
        shelf.clear();
        quantity.clear();
      }
    });
    return addButton;
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
   * Creates a shelf text field with the given shelfName.
   * If the shelfName is empty, the text field will be empty.
   *
   * @param shelfName the name of the shelf
   * @return a styled text field
   */
  private StyledTextField createShelfTextField(String shelfName) {
    StyledTextField shelf = new StyledTextField("Shelf");
    if (!shelfName.isEmpty())  {
      shelf.setText(shelfName);
    }
    return shelf;
  }

  @Override
  public void addObserver(Observer observer) {
    if (observer != null) {
      observers.add(observer);
    } else {
      throw new IllegalArgumentException(OBSERVER_ERROR_MESSAGE);
    }
  }

  @Override
  public void removeObserver(Observer observer) {
    if (observer != null) {
      observers.remove(observer);
    } else {
      throw new IllegalArgumentException(OBSERVER_ERROR_MESSAGE);
    }
  }

  /**
   * Notifies the observers in the observer list with the given buttonEnum.
   *
   * @param buttonEnum the buttonEnum to be sent to the observers
   */
  private void notifyObservers(ButtonEnum buttonEnum, String name, int quantity, String shelf) {
    //TODO: Fix unit
    Grocery grocery = new Grocery(name, quantity, "g", shelf, false);
    List<Observer> observerList = new ArrayList<>(this.observers);
    for (Observer observer : observerList) {
      observer.update(buttonEnum, grocery);
    }
  }
}
