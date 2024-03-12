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
 * An abstract class representing a list element
 */
public abstract class GroceryListElement implements Observable {
  private final Grocery grocery;
  private final List<Observer> observers = new ArrayList<>();

  /**
   * Constructor for the list element
   * @param grocery The grocery to be displayed
   */
  protected GroceryListElement(Grocery grocery) {
    if (grocery != null) {
      this.grocery = grocery;
    } else {
      throw new IllegalArgumentException("Grocery cannot be null");
    }
  }

  /**
   * Retrieves the pane of the list element
   *
   * @return the list element
   */
  public abstract Pane getPane();

  /**
   * Creates a text element
   *
   * @param text the text to be displayed
   * @return the text element
   */
  protected Text createText(String text) {
    Text newText = new Text(text);
    newText.setFont(FontPalette.TEXT);
    return newText;
  }

  /**
   * Creates a HBox-pane with the grocery name, category and amount
   *
   * @param name the name of the grocery
   * @param category the category of the grocery
   * @param amount the amount of the grocery
   * @return the text box
   */
  protected HBox createTextBox(String name, String category, String amount) {
    Text groceryName = createText(name);
    Text groceryCategory = createText(category);
    Text groceryAmount = createText(amount);

    StackPane stackGroceryName = new StackPane(groceryName);
    stackGroceryName.setPadding(new Insets(0, 0, 0, 10));
    stackGroceryName.setMinWidth(20);

    StackPane stackGroceryCategory = new StackPane(groceryCategory);
    stackGroceryCategory.setPadding(new Insets(0, 0, 0, 20));
    stackGroceryName.setMinWidth(20);

    StackPane stackGroceryAmount = new StackPane(groceryAmount);
    stackGroceryAmount.setPadding(new Insets(0, 10, 0, 10));
    stackGroceryName.setMinWidth(20);

    // Add the text to a HBox
    HBox textBox = new HBox(stackGroceryName, stackGroceryCategory, stackGroceryAmount);
    textBox.setSpacing(25);
    textBox.setAlignment(Pos.CENTER);
    return textBox;
  }

  /**
   * Creates a button element with the given text, variant, size and enum.
   * When clicked the button will notify the observers with the given enum.
   *
   * @param text the text to be displayed on the button
   * @param variant the variant of the button
   * @param size the size of the button
   * @param buttonEnum the enum of the button
   * @return the button
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
   * Notifies the observers with the given enum and grocery
   *
   * @param buttonEnum the enum to be notified
   */
  public void notifyObservers(ButtonEnum buttonEnum) {
    for (Observer observer : observers) {
      observer.update(buttonEnum, this.grocery);
    }
  }
}
