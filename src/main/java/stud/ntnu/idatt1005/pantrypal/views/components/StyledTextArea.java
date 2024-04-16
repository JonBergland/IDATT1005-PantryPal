package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.TextArea;

/**
 * A class that extends TextArea and creates a styled text area.
 * Goal: To create a text area with different styles.
 */
public class StyledTextArea extends TextArea {

  /**
   * Constructor for StyledTextArea.
   *
   * @param promptText The prompt text to be displayed in the text area.
   */
  public StyledTextArea(String promptText) {
    super();
    this.setPromptText(promptText);
    this.getStyleClass().add("text-area");
  }
}
