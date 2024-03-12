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
 * A custom TextField with a default style and a search style
 * Goal: Create a custom TextField with a default style and a search style
 */
public class StyledTextField extends TextField {
  public enum Variant {
    DEFAULT, SEARCH
  }
  public StyledTextField(String promptText) {
    super();
    this.setPromptText(promptText);
    this.setDefaultStyle();
  }

  public StyledTextField(String promptText, Variant variant) {
    super();
    this.setPromptText(promptText);
    if (variant == Variant.DEFAULT) {
      this.setDefaultStyle();
    } else if (variant == Variant.SEARCH) {
      this.setSearchStyle();
    }
  }

  public void setDefaultStyle() {
    this.setBackground(new Background(new BackgroundFill(
            ColorPalette.WHITE, new CornerRadii(5), null)));
    this.setBorder(new Border(new BorderStroke(
            ColorPalette.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
  }

  public void setSearchStyle() {
    this.setBackground(new Background(new BackgroundFill(
            ColorPalette.WHITE, null, null)));
    this.setBorder(new Border(new BorderStroke(
            ColorPalette.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

    this.setPrefHeight(100);
    Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
    this.setPrefWidth(primaryScreenBounds.getWidth());

    this.setFont(FontPalette.INPUT_PROMPT_SEARCH);
    this.setPadding(new Insets(0, 0, 0, 20));
  }
}
