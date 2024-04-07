package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;

/**
 * This class represents a favorite button component. It provides methods to create a visual
 * representation of a favorite button, including a star shape and a rectangle background.
 */
public class FavoriteButton extends Button {
  /**
   * The star is an SVGPath that represents the star shape of the favorite button.
   */
  private final SVGPath star;

  /**
   * The boolean isFavorite, which is true if the recipe is a favorite, and false if it is not.
   */
  private boolean isFavorite;

  /**
   * Constructs a FavoriteButton with a star shape and a rectangle background.
   * This constructor sets up the visual representation of the favorite button
   * using the star shape and the rectangle background.
   */
  public FavoriteButton(boolean isFavorite) {
    super();
    this.isFavorite = isFavorite;
    this.star = new SVGPath();
    createStar();
    NodeUtils.addClasses(this, "favorite-button");
  }

  /**
   * Creates the star symbol on the button and sets it as the button's graphic.
   */
  private void createStar() {
    star.setContent("M 15,5 17.5,12.5 25,12.5 20,17.5 22.5,25 15,20 7.5,25 10,17.5 5,"
            + "12.5 12.5,12.5 z");

    // Set the outline color of the star to black
    star.setStroke(Color.BLACK);

    // Set the outline width of the star to be less sharp
    star.setStrokeWidth(2.0);

    // Set the stroke type to outside
    star.setStrokeType(StrokeType.OUTSIDE);
    // Set the star shape as the button graphic
    setGraphic(star);
    setStarColor();

  }

  /**
   * toggles the value of the boolean isFavorite, and updates the background color of the
   * star accordingly.
   */
  public void toggleStarColor() {
    isFavorite = !isFavorite;
    setStarColor();
  }

  /**
   * Sets the background color of the star to yellow if the recipe is a favourite, else transparent.
   */
  public void setStarColor() {
    if (isFavorite) {
      star.setFill(Color.YELLOW);
    } else {
      star.setFill(Color.TRANSPARENT);
    }
  }
}