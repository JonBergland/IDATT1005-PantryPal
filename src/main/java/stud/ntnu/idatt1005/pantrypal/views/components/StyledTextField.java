package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.TextField;

/**
 * A custom TextField with a default style and a search style.
 * Goal: Create a custom TextField with a default style and a search style.
 */
public class StyledTextField extends TextField {

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

}
