package stud.ntnu.idatt1005.pantrypal.views.components;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;

/**
 * This class represents a component for displaying a recipe within the cookbook.
 * It provides methods to create a visual representation of a recipe, including its image and name.
 */
public class CookbookRecipeComponent {

  /**
   * The BorderPane displaying the recipe component.
   */
  private StackPane stackPane;

  /**
   * Constructs a CookbookRecipeComponent for a Recipe.
   * This constructor sets up the visual representation of the recipe
   * using the image if it exists, and the name of the recipe.
   *
   * @param recipe     The recipe to be displayed.
   * @param controller The controller for the cookbook view.
   */
  public CookbookRecipeComponent(Recipe recipe, CookBookController controller) {
    BackgroundSize backgroundSize = new BackgroundSize(320, 200,
            true, true, false, true);
    if (recipe.getImagePath() == null || recipe.getImagePath().isEmpty()) {
      BackgroundFill backgroundFill = new BackgroundFill(ColorPalette.GRAY,
              new CornerRadii(10), null);
      setUpBorderPane(controller, recipe);
      stackPane.setBackground(new Background(backgroundFill));
    } else {
      Image image = new Image(recipe.getImagePath());
      BackgroundImage backgroundImage = new BackgroundImage(image,
              BackgroundRepeat.NO_REPEAT,
              BackgroundRepeat.NO_REPEAT,
              BackgroundPosition.CENTER,
              backgroundSize);
      setUpBorderPane(controller, recipe);
      stackPane.setBackground(new Background(backgroundImage));
    }
    setLabel(recipe.getKey());
    setStarIcon(recipe.getIsFavorite());
  }


  /**
   * Sets up the BorderPane for the recipe component.
   *
   * @param controller The controller for the cookbook view.
   * @param recipe     The recipe to be displayed.
   */
  private void setUpBorderPane(CookBookController controller, Recipe recipe) {
    stackPane = new StackPane();
    stackPane.setMaxWidth(getWidth());
    stackPane.setMaxHeight(getHeight());
    stackPane.setMinWidth(getWidth());
    stackPane.setMinHeight(getHeight());
    BorderStroke borderStroke = new BorderStroke(ColorPalette.BLACK,
            BorderStrokeStyle.SOLID,
            new CornerRadii(10),
            new BorderWidths(1));
    stackPane.setBorder(new Border(borderStroke));
    Rectangle clip = new Rectangle(getWidth(), 200);
    clip.setArcWidth(20);
    clip.setArcHeight(20);
    stackPane.setClip(clip);
    stackPane.setOnMouseClicked(e -> controller.openRecipe(recipe));
  }

  /**
   * Sets the label of the recipe component.
   *
   * @param recipeName name of the recipe
   */
  private void setLabel(String recipeName) {
    Label label = new Label(recipeName);
    label.setFont(FontPalette.BUTTON);
    StackPane.setAlignment(label, Pos.CENTER);
    stackPane.getChildren().add(label);
  }

  /**
   * Sets the star icon of the recipe component, and places it in the top right corner.
   *
   * @param isFavorite boolean indicating if the recipe is a favorite
   */
  private void setStarIcon(boolean isFavorite) {
    StarIcon starIcon = new StarIcon(StarIcon.Variants.COOKBOOK, isFavorite);
    StackPane.setAlignment(starIcon, Pos.TOP_RIGHT);
    stackPane.getChildren().add(starIcon);
  }

  /**
   * Returns the BorderPane representing the recipe component.
   *
   * @return The BorderPane representing the recipe component.
   */
  public StackPane getComponent() {
    return stackPane;
  }

  /**
   * Calculates and returns the width of the recipe component.
   *
   * @return The width of the recipe component.
   */
  public static double getWidth() {
    Rectangle2D visualBounds = getPrimary().getVisualBounds();
    return visualBounds.getWidth() / 4 - 40;
  }

  /**
   * Calculates and returns the height of the recipe component.
   *
   * @return The height of the recipe component.
   */
  public static double getHeight() {
    return getWidth() * 0.625;
  }
}
