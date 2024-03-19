package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Font;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

/**
 * A class that extends Hyperlink and creates a hyperlink with a route to a scene.
 * Goal: To create a hyperlink that can be used to navigate between scenes.
 */
public class NavLink extends Hyperlink {

  /**
   * Constructor for NavLink.
   *
   * @param text The text to be displayed on the hyperlink.
   */
  public NavLink(String text) {
    super(text);
    defaultStyle();
    this.setFocusTraversable(false);
  }

  /**
   * Sets the default style for the hyperlink.
   */
  private void defaultStyle() {
    this.setTextFill(ColorPalette.BLACK);
    this.setFont(Font.font("roboto", 16));
    this.setPadding(new Insets(5, 10, 5, 10));
  }

  /**
   * Get the hyperlink.
   *
   * @return The hyperlink
   */
  public Hyperlink getLink() {
    return this;
  }
}
