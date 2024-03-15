package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

/**
 * A class that extends Button and creates a styled button.
 * Goal: To create a button with different styles and sizes.
 */
public class StyledButton extends Button {

  /**
   * Enum for the different variants of the button.
   */
  public enum Variant {
    SOLID, OUTLINE, DANGER, BLACK
  }

  /**
   * Enum for the different sizes of the button.
   */
  public enum Size {
    MEDIUM, LARGE
  }

  /**
   * Constructor for StyledButton.
   * Sets the default style for the button.
   *
   * @param text The text to be displayed on the button.
   */
  public StyledButton(String text) {
    super(text);
    this.getStyleClass().addAll("button", "button-medium", "button-solid");
  }

  private void addStyleClass(String style) {
    this.getStyleClass().add(style);
  }

  private void addStyleClasses(String... styles) {
    this.getStyleClass().addAll(styles);
  }

  /**
   * Constructor for StyledButton.
   * Sets the default style for the button.
   *
   * @param text The text to be displayed on the button.
   * @param variant The variant of the button.
   */
  public StyledButton(String text, Variant variant) {
    super(text);
    this.addStyleClasses("button", "button-medium");

    if (variant == Variant.SOLID) {
      this.addStyleClass("button-solid");
    }
    if (variant == Variant.OUTLINE) {
      this.addStyleClass("button-outline");
    }
    if (variant == Variant.DANGER) {
      this.addStyleClass("button-danger");
    }
    if (variant == Variant.BLACK) {
      this.addStyleClass("button-black");
    }
  }

  /**
   * Constructor for StyledButton.
   * Sets the default style for the button.
   *
   * @param text The text to be displayed on the button.
   * @param size The size of the button.
   */
  public StyledButton(String text, Size size) {
    super(text);
    this.addStyleClasses("button", "button-solid");

    if (size == Size.MEDIUM) {
      this.addStyleClass("button-medium");
    }
    if (size == Size.LARGE) {
      this.addStyleClass("button-large");
    }
  }

  /**
   * Constructor for StyledButton.
   * Sets the default style for the button.
   *
   * @param text The text to be displayed on the button.
   * @param variant The variant of the button.
   * @param size The size of the button.
   */
  public StyledButton(String text, Variant variant, Size size) {
    super(text);
    this.addStyleClass("button");

    if (size == Size.MEDIUM) {
      this.addStyleClass("button-medium");
    }
    if (size == Size.LARGE) {
        this.addStyleClass("button-large");
    }

    if (variant == Variant.SOLID) {
      this.addStyleClass("button-solid");
    }
    if (variant == Variant.OUTLINE) {
      this.addStyleClass("button-outline");
    }
    if (variant == Variant.DANGER) {
      this.addStyleClass("button-danger");
    }
    if (variant == Variant.BLACK) {
      this.addStyleClass("button-black");
    }
  }
}
