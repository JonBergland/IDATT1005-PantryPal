package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Rectangle;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;

/**
 * This class represents a component for displaying a recipe within a cookbook.
 * It provides methods to create a visual representation of a recipe, including its image and name.
 */
public class CookbookRecipeComponent {

  private BorderPane borderPane;

  /**
   * Constructs a CookbookRecipeComponent with an image and recipe name.
   * This constructor sets up the visual representation of the recipe
   * using the provided image and name.
   *
   * @param image      The image representing the recipe.
   * @param recipeName The name of the recipe.
   */
  public CookbookRecipeComponent(Image image, String recipeName) {
    // Set up the background with the provided image
    BackgroundSize backgroundSize = new BackgroundSize(320, 200,
            true, true, false, true);
    BackgroundImage backgroundImage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            backgroundSize);
    setUpBorderPane();
    borderPane.setBackground(new Background(backgroundImage));
    setLabel(recipeName);
  }

  /**
   * Constructs a CookbookRecipeComponent with only a recipe name.
   * This constructor sets up the visual representation of the recipe
   * with a default background color and the provided name.
   *
   * @param recipeName The name of the recipe.
   */
  public CookbookRecipeComponent(String recipeName) {
    BackgroundFill backgroundFill = new BackgroundFill(ColorPalette.GRAY,
            new CornerRadii(10), null);
    setUpBorderPane();
    borderPane.setBackground(new Background(backgroundFill));
    setLabel(recipeName);
  }

  /**
   * Sets up the BorderPane for the recipe component.
   */
  private void setUpBorderPane() {
    borderPane = new BorderPane();
    borderPane.setMaxWidth(320);
    borderPane.setMaxHeight(200);
    BorderStroke borderStroke = new BorderStroke(ColorPalette.BLACK,
            BorderStrokeStyle.SOLID,
            new CornerRadii(10),
            new BorderWidths(1));
    borderPane.setBorder(new Border(borderStroke));
    Rectangle clip = new Rectangle(320, 200);
    clip.setArcWidth(20);
    clip.setArcHeight(20);
    borderPane.setClip(clip);
    borderPane.setOnMouseClicked(e -> System.out.println("Recipe clicked"));
  }

  /**
   * Sets the label of the recipe component.
   *
   * @param recipeName name of the recipe
   */
  private void setLabel(String recipeName) {
    Label label = new Label(recipeName);
    label.setFont(FontPalette.BUTTON);
    borderPane.setCenter(label);
  }

  /**
   * Returns the BorderPane representing the recipe component.
   *
   * @return The BorderPane representing the recipe component.
   */
  public BorderPane getBorderPane() {
    return borderPane;
  }
}