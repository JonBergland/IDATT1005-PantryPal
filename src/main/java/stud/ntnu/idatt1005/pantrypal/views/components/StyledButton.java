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
    this.medium();
    this.solid();
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
    this.medium();

    if (variant == Variant.SOLID) {
      this.solid();
    }
    if (variant == Variant.OUTLINE) {
      this.outline();
    }
    if (variant == Variant.DANGER) {
      this.danger();
    }
    if (variant == Variant.BLACK) {
      this.black();
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
    this.solid();

    if (size == Size.MEDIUM) {
      this.medium();
    }
    if (size == Size.LARGE) {
      this.large();
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

    if (size == Size.MEDIUM) {
      this.medium();
    }
    if (size == Size.LARGE) {
      this.large();
    }

    if (variant == Variant.SOLID) {
      this.solid();
    }
    if (variant == Variant.OUTLINE) {
      this.outline();
    }
    if (variant == Variant.DANGER) {
      this.danger();
    }
    if (variant == Variant.BLACK) {
      this.black();
    }
  }

  /**
   * Sets the style for a medium button.
   */
  private void medium() {
    this.setPadding(new Insets(10));
    this.setFont(Font.font("roboto", FontWeight.MEDIUM, 16));
  }

  /**
   * Sets the style for a large button.
   */
  private void large() {
    this.setPadding(new Insets(15));
    this.setFont(Font.font("roboto", FontWeight.MEDIUM, 20));
  }

  /**
   * Sets the style for a solid button.
   */
  private void solid() {
    this.setBackground(
        new Background(new BackgroundFill(ColorPalette.PRIMARY, new CornerRadii(5), null)));
    this.setTextFill(ColorPalette.WHITE);

    this.setOnMouseEntered(e -> this.setBackground(
        new Background(new BackgroundFill(ColorPalette.PRIMARY_DARK, new CornerRadii(5), null))));
    this.setOnMouseExited(e -> this.setBackground(
        new Background(new BackgroundFill(ColorPalette.PRIMARY, new CornerRadii(5), null))));
  }

  /**
   * Sets the style for an outlined button.
   */
  private void outline() {
    this.setBackground(new Background(new BackgroundFill(
        ColorPalette.PRIMARY_LIGHT, new CornerRadii(5), null)));
    this.setBorder(new Border(new BorderStroke(
        ColorPalette.PRIMARY_DARK, BorderStrokeStyle.DASHED, new CornerRadii(5),
        new BorderWidths(2))));
    this.setTextFill(ColorPalette.PRIMARY_DARK);
  }

  /**
   * Sets the style for a danger button.
   */
  private void danger() {
    this.setBackground(new Background(new BackgroundFill(
        ColorPalette.DANGER, new CornerRadii(5), null)));
    this.setTextFill(ColorPalette.WHITE);
  }

  /**
   * Sets the style for a black button.
   */
  private void black() {
    this.setBackground(new Background(new BackgroundFill(
        ColorPalette.BLACK, new CornerRadii(5), null)));
    this.setTextFill(ColorPalette.WHITE);
  }
}
