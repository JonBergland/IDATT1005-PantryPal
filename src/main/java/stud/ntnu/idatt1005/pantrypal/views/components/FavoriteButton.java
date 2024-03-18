package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

public class FavoriteButton extends Button {

  private final BooleanProperty isFavorite;
  private final SVGPath star;

  private final StackPane stackPane;
  public FavoriteButton() {
    super();

    // Create a less sharp star shape using SVG
    star = new SVGPath();
    star.setContent("M 15,5 17.5,12.5 25,12.5 20,17.5 22.5,25 15,20 7.5,25 10,17.5 5,12.5 12.5,12.5 z");

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

    Rectangle starBackground = new Rectangle(45, 45);
    starBackground.setFill(ColorPalette.PRIMARY);
    starBackground.setArcWidth(15);
    starBackground.setArcHeight(15);

    stackPane = new StackPane();
    stackPane.getChildren().addAll(starBackground, star);

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

  public StackPane getFavoriteButton() {
    return stackPane;
  }

}