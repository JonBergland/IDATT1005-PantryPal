package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import stud.ntnu.idatt1005.pantrypal.enums.StarEnum;

/**
 * This class represents a star icon component, to be used to show if a recipe is a favorite.
 * The constructor takes in a StarEnum and a boolean isFavorite, and sets up the visual
 * representation based on where its used, and color based on if the recipe is a favorite or not.
 * The setColor method sets the fill color of the star based on if the recipe is a favorite or not.
 */
public class StarIcon extends SVGPath {

  /**
   * Constructs a StarIcon with a star shape and a rectangle background.
   * This constructor sets up the visual representation of the star icon
   * using the star shape and the rectangle background.
   *
   * @param variant    The variant of the star icon.
   * @param isFavorite The boolean isFavorite, which is true if the recipe is a favorite, and
   *                   false if it is not.
   */
  public StarIcon(StarEnum variant, boolean isFavorite) {
    this.setContent("M 15,5 17.5,12.5 25,12.5 20,17.5 22.5,25 15,20 7.5,25 10,17.5 5,"
            + "12.5 12.5,12.5 z");
    this.setStroke(Color.BLACK);
    this.setStrokeWidth(2.0);
    this.setStrokeType(StrokeType.OUTSIDE);

    if (variant == StarEnum.RECIPE) {
      this.setScaleX(1);
      this.setScaleY(1);
    } else if (variant == StarEnum.COOKBOOK) {
      this.setScaleX(0.6);
      this.setScaleY(0.6);
    }
    setColor(isFavorite);
  }

  /**
   * Sets the fill color of the star based on if the recipe is a favorite or not.
   *
   * @param isFavorite The boolean isFavorite, which is true if the recipe is a favorite,
   *                   and false if it is not.
   */
  public void setColor(boolean isFavorite) {
    if (isFavorite) {
      this.setFill(Color.YELLOW);
    } else {
      this.setFill(Color.TRANSPARENT);
    }
  }
}
