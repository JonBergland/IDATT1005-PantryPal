package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;

/**
 * This class represents a favorite button component. It provides methods to create a visual
 * representation of a favorite button, including a star shape and a rectangle background.
 */
public class FavoriteButton extends Button {
  /**
   * The isFavorite property is a boolean property that represents whether
   * the recipe is a favorite or not.
   */
  private final BooleanProperty isFavorite;

  /**
   * The star is an SVGPath that represents the star shape of the favorite button.
   */
  private final SVGPath star;

  /**
   * The stackPane is a StackPane that holds the star and the rectangle background.
   */
  private final StackPane stackPane;

  /**
   * Constructs a FavoriteButton with a star shape and a rectangle background.
   * This constructor sets up the visual representation of the favorite button
   * using the star shape and the rectangle background.
   */
  public FavoriteButton(Recipe recipe) {
    super();

    // Create a less sharp star shape using SVG
    star = new SVGPath();
    star.setContent("M 15,5 17.5,12.5 25,12.5 20,17.5 22.5,25 15,20 7.5,25 10,17.5 5,12.5 12.5,12.5 z");

    // Set the initial fill color of the star to the same color as the rectangle
    star.setFill(Color.TRANSPARENT);

    // Set the outline color of the star to black
    star.setStroke(Color.BLACK);

    // Set the outline width of the star to be less sharp
    star.setStrokeWidth(2.0);

    // Set the stroke type to outside
    star.setStrokeType(StrokeType.OUTSIDE);

    // Set the star shape as the button graphic
    setGraphic(star);

    // Create a rectangle to serve as the background of the star
    Rectangle starBackground = new Rectangle(45, 45);
    starBackground.setFill(Color.TRANSPARENT);

    // Create a stack pane to hold the star and the rectangle
    stackPane = new StackPane();
    stackPane.getChildren().addAll(starBackground, star);
    NodeUtils.addClasses(stackPane, "favorite-button");

    // Initialize the isFavorite property
    isFavorite = new SimpleBooleanProperty(stackPane, "isFavorite", recipe.getIsFavorite());
    setFavoriteButton(recipe.getIsFavorite());

    // Add a listener to the isFavorite property
    isFavorite.addListener((obs, wasFavorite, isNowFavorite) -> {
      recipe.setIsFavorite(isNowFavorite);
      setFavoriteButton(isNowFavorite);
    });

    // Set the action to toggle the isFavorite property when the button is clicked
    stackPane.setOnMouseClicked(event -> isFavorite.set(!isFavorite.get()));
  }

  /**
   * Sets the fill color of the star based on the isFavorite property.
   *
   * @param isFavorite the isFavorite property
   */
  private void setFavoriteButton(boolean isFavorite) {
    if (isFavorite) {
      star.setFill(Color.YELLOW);
    } else {
      star.setFill(Color.TRANSPARENT);
    }

  }

  /**
   * Returns the isFavorite property.
   *
   * @return the isFavorite property
   */
  public StackPane getFavoriteButton() {
    return stackPane;
  }

}