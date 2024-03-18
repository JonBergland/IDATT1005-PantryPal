package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;

public class FavoriteButton extends Button {

  private final BooleanProperty isFavorite;
  private final SVGPath star;

  public FavoriteButton() {
    super();

    // Create a less sharp star shape using SVG
    star = new SVGPath();
    star.setContent("M 30,10 35,25 50,25 40,35 45,50 30,40 15,50 20,35 10,25 25,25 z");

    // Set the initial fill color of the star to the same color as the rectangle
    star.setFill(Color.web("#8BC34A"));

    // Set the outline color of the star to black
    star.setStroke(Color.BLACK);

    // Set the outline width of the star to be less sharp
    star.setStrokeWidth(2.0);

    // Set the stroke type to outside
    star.setStrokeType(StrokeType.OUTSIDE);

    // Set the star shape as the button graphic
    setGraphic(star);

    // Set the background color of the Button to the same color as the star
    setStyle("-fx-background-color: #8BC34A;");

    // Initialize the isFavorite property
    isFavorite = new SimpleBooleanProperty(this, "isFavorite", false);

    // Add a listener to the isFavorite property
    isFavorite.addListener((obs, wasFavorite, isNowFavorite) -> {
      if (isNowFavorite) {
        // If the button is clicked, set the fill color of the star to yellow
        star.setFill(Color.YELLOW);
      } else {
        // If the button is not clicked, set the fill color of the star back to the same color as the rectangle
        star.setFill(Color.web("#8BC34A"));
      }
    });

    // Set the action to toggle the isFavorite property when the button is clicked
    setOnAction(event -> isFavorite.set(!isFavorite.get()));
  }

  public FavoriteButton getFavoriteButton() {
    return this;
  }

}