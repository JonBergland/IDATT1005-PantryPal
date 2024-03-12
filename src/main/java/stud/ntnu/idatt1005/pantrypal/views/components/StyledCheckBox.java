package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

/**
 * A class that extends CheckBox and creates a styled checkbox.
 * Goal: To create a checkbox with different styles and sizes.
 */
public class StyledCheckBox extends CheckBox {

  // Sets the default style for the checkbox.
  public StyledCheckBox(String text) {
    super(text);
    this.setPadding(new Insets(10));
  }

  /**
   * Sets the default style for a checkbox.
   *
   * @param text The text to be displayed on the checkbox.
   * @param selected The selected state of the checkbox.
   */
  public StyledCheckBox(String text, boolean selected) {
    super(text);
    this.setSelected(selected);
    this.setPadding(new Insets(10));
  }

  /**
   * Sets the default style for a checkbox.
   *
   * @param text The text to be displayed on the checkbox.
   * @param selected The selected state of the checkbox.
   * @param variant The variant of the checkbox.
   */
  public StyledCheckBox(String text, boolean selected, String variant) {
    super(text);
    this.setSelected(selected);
    this.setPadding(new Insets(10));
    if (variant.equals("default") || variant.isEmpty()) {
      this.setBackground(new Background(new BackgroundFill(ColorPalette.WHITE, null, null)));
    }
  }

}
