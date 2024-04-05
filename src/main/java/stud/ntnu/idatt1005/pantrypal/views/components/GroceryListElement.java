package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
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
 * Class representing a shopping list element. This class implement the Observable interface
 * and provides a visual representation of a grocery item in the shopping list.
 * It includes a checkbox, text information about the grocery item, and a delete button.
 */
public class GroceryListElement implements Observable {

  private static List<Observer> observers = new ArrayList<>();

  /**
   * The {@link BorderPane} containing the visual elements of the shopping list item.
   * This includes a checkbox, text information about the grocery item, and a delete button.
   */
  private final BorderPane pane;

  /**
   * Constructor for the shopping list element. It initializes the visual elements
   * and sets up the necessary event handlers.
   */
  private GroceryListElement(GroceryListElementBuilder builder) {
    this.pane = builder.pane;
  }

  /**
   * Retrieves the visual representation of the shopping list element.
   *
   * @return the {@link Pane} containing the visual elements of the shopping list item.
   */
  public Pane getPane() {
    return pane;
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
   * A builder class representing a list element in the shopping list.
   * This class provides a visual representation of a grocery item in the shopping list.
   * It includes a checkbox, text information about the grocery item, and a delete button.
   * This class also implements the Observable interface, allowing it to notify observers of changes.
   */
  public static class GroceryListElementBuilder {
    private final Grocery grocery;
    private final StackPane checkPane = new StackPane();
    private final HBox textBox = new HBox();
    private Button deleteButton = new Button();
    private final BorderPane pane = new BorderPane();

    public GroceryListElementBuilder(Grocery grocery) {
      if (grocery != null) {
        this.grocery = grocery;
      } else {
        throw new IllegalArgumentException("Grocery cannot be null");
      }
    }

    public GroceryListElementBuilder checkBox() {
      CheckBox checkBox1 = new CheckBox("");
      checkBox1.setSelected(grocery.getChecked());
      checkBox1.setOnAction(event -> grocery.setChecked(checkBox1.isSelected()));

      checkPane.getChildren().add(checkBox1);
      return this;
    }

    public GroceryListElementBuilder text(String text) {
      Text newText = new Text(text);
      newText.setFont(FontPalette.TEXT);

      newText.setWrappingWidth(100);

      StackPane textPane = new StackPane(newText);
      textPane.setPadding(new Insets(0, 0, 0, 10));

      textBox.getChildren().add(textPane);
      return this;
    }

    public GroceryListElementBuilder quantity() {
      Spinner<Integer> spinner = createSpinner();
      spinner.setMaxWidth(100);
      spinner.setMaxHeight(50);

      textBox.getChildren().add(spinner);
      return this;
    }

    public GroceryListElementBuilder button(String text, StyledButton.Variant variant, StyledButton.Size size, ButtonEnum buttonEnum) {
      deleteButton = createButton(text, variant, size, buttonEnum);
      return this;
    }

    public GroceryListElement build() {
      pane.setLeft(checkPane);
      textBox.setAlignment(Pos.CENTER);
      pane.setCenter(textBox);
      pane.setRight(deleteButton);
      pane.getStyleClass().add("shopping-list-element");

      return new GroceryListElement(this);
    }

    private Spinner<Integer> createSpinner() {
      Spinner<Integer> spinner = new Spinner<>();

      final Grocery finalGrocery = this.grocery;

      SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, finalGrocery.getQuantity());

      valueFactory.setAmountToStepBy(1);

      valueFactory.valueProperty().addListener((observable, oldValue, newValue) ->
          finalGrocery.setQuantity(newValue)
      );

      spinner.setValueFactory(valueFactory);
      return spinner;
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
    private StyledButton createButton(String text, StyledButton.Variant variant,
                                        StyledButton.Size size, ButtonEnum buttonEnum) {
      StyledButton newButton = new StyledButton(text, variant, size);
      newButton.setOnAction(e -> notifyObservers(buttonEnum));
      return newButton;
    }

    /**
     * Notifies the observers with the given enum and grocery.
     *
     * @param buttonEnum the enum to be notified.
     */
    protected void notifyObservers(ButtonEnum buttonEnum) {
      for (Observer observer : observers) {
        observer.update(buttonEnum, this.grocery);
      }
    }
  }
}