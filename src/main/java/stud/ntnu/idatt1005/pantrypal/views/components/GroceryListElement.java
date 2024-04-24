package stud.ntnu.idatt1005.pantrypal.views.components;

import java.util.ArrayList;
import java.util.List;
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
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.utils.SoundPlayer;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

/**
 * Class representing a shopping list element. This class implement the Observable interface
 * and provides a visual representation of a grocery item in the shopping list.
 * It includes a checkbox, text information about the grocery item, and a delete button.
 */
public class GroceryListElement implements Observable {

  private final List<Observer> observers = new ArrayList<>();

  /**
   * The {@link BorderPane} containing the visual elements of the shopping list item.
   * This includes a checkbox, text information about the grocery item, and a delete button.
   */
  private final BorderPane pane = new BorderPane();
  private final Grocery grocery;

  /**
   * Constructor for the shopping list element. It initializes the visual elements
   * and sets up the necessary event handlers.
   */
  private GroceryListElement(GroceryListElementBuilder builder) {
    grocery = builder.grocery;
    StackPane checkPane = builder.checkPane;
    HBox textBox = builder.textBox;
    textBox.setAlignment(Pos.CENTER);
    Button deleteButton = createButton();

    pane.setLeft(checkPane);
    pane.setCenter(textBox);
    pane.setRight(deleteButton);
    pane.getStyleClass().add("shopping-list-element");
  }

  /**
   * Retrieves the visual representation of the shopping list element.
   *
   * @return the {@link Pane} containing the visual elements of the shopping list item.
   */
  public Pane getPane() {
    return pane;
  }

  /**
   * Creates a button element with the given text, variant, size and enum.
   * When clicked the button will notify the observers with the given enum.
   *
   * @return the StyledButton with the specified properties.
   */
  private StyledButton createButton() {
    StyledButton newButton = new StyledButton(
        "X", StyledButton.Variant.DELETE, StyledButton.Size.MEDIUM);
    newButton.setOnAction(e -> {
      notifyObservers(ButtonEnum.REMOVE);
      SoundPlayer.playSound(SoundPlayer.Sound.DELETE);
    });
    return newButton;
  }

  @Override
  public void addObserver(Observer observer) {
    if (observer != null) {
      if (!observers.contains(observer)) {
        observers.add(observer);
      }
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

  /**
   * Notifies the observers with the given enum and grocery.
   *
   * @param buttonEnum the enum to be notified.
   */
  protected void notifyObservers(ButtonEnum buttonEnum) {
    List<Observer> observersCopy = new ArrayList<>(this.observers);
    for (Observer observer : observersCopy) {
      observer.update(buttonEnum, this.grocery);
    }
  }

  /**
   * A builder class representing a list element in the shopping list.
   * This class provides a visual representation of a grocery item in the shopping list.
   * It includes a checkbox, text information about the grocery item, and a delete button.
   * This class also implements the Observable interface,
   * allowing it to notify observers of changes.
   */
  public static class GroceryListElementBuilder {
    private final Grocery grocery;
    private final StackPane checkPane = new StackPane();
    private final HBox textBox = new HBox();

    /**
     * Constructor for the GroceryListElementBuilder.
     *
     * @param grocery the grocery item to be represented by the shopping list element.
     * @throws IllegalArgumentException if the grocery item is null.
     */
    public GroceryListElementBuilder(Grocery grocery) {
      if (grocery != null) {
        this.grocery = grocery;
      } else {
        throw new IllegalArgumentException("Grocery cannot be null");
      }
    }

    /**
     * Creates a new CheckBox and ads it to the GroceryListElementBuilder.
     *
     * @return a new GroceryListElementBuilder with the given grocery item.
     */
    public GroceryListElementBuilder checkBox() {
      CheckBox checkBox = new CheckBox("");
      checkBox.setPadding(new Insets(10));
      checkBox.setSelected(grocery.getChecked());
      checkBox.setOnAction(event -> grocery.setChecked(checkBox.isSelected()));

      checkPane.getChildren().add(checkBox);
      return this;
    }

    /**
     * Adds a text element to the GroceryListElementBuilder.
     *
     * @param text the text to be displayed in the text element.
     * @return a new GroceryListElementBuilder with the given text.
     */
    public GroceryListElementBuilder text(String text) {
      Text newText = new Text(text);
      newText.setFont(FontPalette.TEXT);

      newText.setWrappingWidth(100);

      StackPane textPane = new StackPane(newText);
      textPane.setPadding(new Insets(0, 0, 0, 10));

      textBox.getChildren().add(textPane);
      return this;
    }

    /**
     * Adds a quantity element to the GroceryListElementBuilder.
     * The quantity element is a spinner that allows the user to
     * change the quantity of the grocery item.
     *
     * @return a new GroceryListElementBuilder with the given quantity.
     */
    public GroceryListElementBuilder quantity() {
      Spinner<Integer> spinner = createSpinner();
      spinner.setMaxWidth(100);
      spinner.setMaxHeight(50);
      spinner.setPadding(new Insets(0, 0, 0, 10));

      textBox.getChildren().add(spinner);
      return this;
    }

    /**
     * Creates a spinner element with the given properties.
     * The spinner element is a spinner that allows the user to
     * change the quantity of the grocery item.
     *
     * @return a new Spinner element with the given properties.
     */
    private Spinner<Integer> createSpinner() {
      Spinner<Integer> spinner = new Spinner<>();

      final Grocery finalGrocery = this.grocery;

      SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, finalGrocery.getQuantity());
      valueFactory.setAmountToStepBy(1);

      valueFactory.valueProperty().addListener((observable, oldValue, newValue) ->
          finalGrocery.setQuantity(newValue)
      );

      spinner.setValueFactory(valueFactory);
      spinner.setEditable(true);
      return spinner;
    }

    /**
     * Builds the GroceryListElement with the given properties.
     *
     * @return a new GroceryListElement with the given properties.
     */
    public GroceryListElement build() {
      return new GroceryListElement(this);
    }
  }
}