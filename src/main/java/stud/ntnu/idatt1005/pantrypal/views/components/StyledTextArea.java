package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

public class StyledTextArea extends TextArea {

  public StyledTextArea(String promptText) {
    super();
    this.setPromptText(promptText);
    this.getStyleClass().add("text-area");
  }
}
