package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.Button;
import stud.ntnu.idatt1005.pantrypal.enums.StarEnum;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;

/**
 * This class represents a favorite button component. It provides methods to create a visual
 * representation of a favorite button, including a star shape and a rectangle background.
 */
public class FavoriteButton extends Button {
  /**
   * The star is an SVGPath that represents the star shape of the favorite button.
   */
  private final StarIcon star;

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
    this.star = new StarIcon(StarEnum.RECIPE, isFavorite);
    setGraphic(star);
    NodeUtils.addClasses(this, "favorite-button");
  }

  /**
   * toggles the value of the boolean isFavorite, and updates the background color of the
   * star accordingly.
   */
  public void toggleStarColor() {
    isFavorite = !isFavorite;
    star.setColor(isFavorite);
  }
}