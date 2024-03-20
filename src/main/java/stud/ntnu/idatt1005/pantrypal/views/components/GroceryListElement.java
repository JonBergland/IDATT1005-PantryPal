package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class representing a list element in the shopping list.
 * This class provides a visual representation of a grocery item in the shopping list.
 * It includes a checkbox, text information about the grocery item, and a delete button.
 * This class also implements the Observable interface, allowing it to notify observers of changes.
 */
public abstract class GroceryListElement implements Observable {
  private final Grocery grocery;
  private final List<Observer> observers = new ArrayList<>();

  /**
   * Constructor for the list element. It initializes the grocery item to be displayed.
   *
   * @param grocery   The grocery item to be displayed in the list element.
   * @throws IllegalArgumentException if the grocery item is null.
   */
  protected GroceryListElement(Grocery grocery) {
    if (grocery != null) {
      this.grocery = grocery;
    } else {
      throw new IllegalArgumentException("Grocery cannot be null");
    }
  }

  /**
   * Retrieves the visual representation of the list element.
   *
   * @return the Pane containing the visual elements of the list item.
   */
  public abstract Pane getPane();

  /**
   * Creates a text element with the specified text.
   *
   * @param text  the text to be displayed in the text element.
   * @return the Text element with the specified text.
   */
  protected Text createText(String text) {
    Text newText = new Text(text);
    newText.setFont(FontPalette.TEXT);
    return newText;
  }

  /**
   * Creates a HBox-pane with the grocery name, category and amount.
   *
   * @param name      the name of the grocery.
   * @param category  the category of the grocery.
   * @param amount    the amount of the grocery.
   * @return the HBox containing the grocery name, category and amount.
   */
  protected HBox createTextBox(String name, String category, String amount) {
    Text groceryName = createText(name);
    Text groceryCategory = createText(category);
    Text groceryAmount = createText(amount);

    groceryName.setWrappingWidth(100);
    groceryCategory.setWrappingWidth(100);
    groceryAmount.setWrappingWidth(100);

    StackPane stackGroceryName = new StackPane(groceryName);
    stackGroceryName.setPadding(new Insets(0, 0, 0, 10));

    StackPane stackGroceryCategory = new StackPane(groceryCategory);
    stackGroceryCategory.setPadding(new Insets(0, 0, 0, 20));

    StackPane stackGroceryAmount = new StackPane(groceryAmount);
    stackGroceryAmount.setPadding(new Insets(0, 10, 0, 10));

    // Add the text to a HBox
    HBox textBox = new HBox(stackGroceryName, stackGroceryCategory, stackGroceryAmount);
    textBox.setAlignment(Pos.CENTER_RIGHT);
    return textBox;
  }


  /**
   * Creates a button element with the given text, variant, size and enum.
   * When clicked the button will notify the observers with the given enum.
   *
   * @param text        the text to be displayed on the button.
   * @param variant     the variant of the button.
   * @param size        the size of the button.
   * @param buttonEnum  the enum of the button.
   * @return the StyledButton with the specified properties.
   */
  protected StyledButton createButton(String text, StyledButton.Variant variant,
                                      StyledButton.Size size, ButtonEnum buttonEnum) {
    StyledButton newButton = new StyledButton(text, variant, size);
    newButton.setOnAction(e -> notifyObservers(buttonEnum));
    return newButton;
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  /**
   * Notifies the observers with the given enum and grocery.
   *
   * @param buttonEnum the enum to be notified.
   */
  public void notifyObservers(ButtonEnum buttonEnum) {
    for (Observer observer : observers) {
      observer.update(buttonEnum, this.grocery);
    }
  }
}
