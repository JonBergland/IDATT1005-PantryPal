package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;

/**
 * A custom TextField with a default style and a search style.
 * Goal: Create a custom TextField with a default style and a search style.
 */
public class StyledTextField extends TextField {
  /**
   * Enum for the different variants of the text field.
   */
  public enum Variant {
    DEFAULT, SEARCH
  }

  /**
   * Constructor for StyledTextField.
   * Sets the default style for the text field.
   *
   * @param promptText The text to be displayed on the text field.
   */
  public StyledTextField(String promptText) {
    super();
    this.setPromptText(promptText);
    this.getStyleClass().add("text-field");
  }

  /**
   * Constructor for StyledTextField.
   * Sets the default style for the text field.
   *
   * @param promptText The text to be displayed on the text field.
   * @param variant The variant of the text field.
   */
  public StyledTextField(String promptText, Variant variant) {
    super();
    this.setPromptText(promptText);
    this.getStyleClass().add("text-field");
  }
}
