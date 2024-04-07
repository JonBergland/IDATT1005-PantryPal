package stud.ntnu.idatt1005.pantrypal.views.components;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Rectangle2D;
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

import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a component for displaying a recipe within the cookbook.
 * It provides methods to create a visual representation of a recipe, including its image and name.
 */
public class CookbookRecipeComponent implements Observable {

  private final Recipe recipe;

  /**
   * The BorderPane displaying the recipe component.
   */
  private BorderPane borderPane;
  private final List<Observer> observers = new ArrayList<>();

  /**
   * Constructs a CookbookRecipeComponent for a Recipe.
   * This constructor sets up the visual representation of the recipe
   * using the image if it exists, and the name of the recipe.
   *
   * @param recipe     The recipe to be displayed.
   */
  public CookbookRecipeComponent(Recipe recipe) {
    this.recipe = recipe;
    BackgroundSize backgroundSize = new BackgroundSize(320, 200,
            true, true, false, true);
    setUpBorderPane();

    if (recipe.getImagePath() == null || recipe.getImagePath().isEmpty()) {
      BackgroundFill backgroundFill = new BackgroundFill(ColorPalette.GRAY,
              new CornerRadii(10), null);
      borderPane.setBackground(new Background(backgroundFill));
    } else {
      Image image = new Image(recipe.getImagePath());
      BackgroundImage backgroundImage = new BackgroundImage(image,
              BackgroundRepeat.NO_REPEAT,
              BackgroundRepeat.NO_REPEAT,
              BackgroundPosition.CENTER,
              backgroundSize);
      borderPane.setBackground(new Background(backgroundImage));
    }

    setLabel(recipe.getKey());
  }


  /**
   * Sets up the BorderPane for the recipe component.
   *
   */
  private void setUpBorderPane() {
    borderPane = new BorderPane();
    borderPane.setMaxWidth(getWidth());
    borderPane.setMaxHeight(getHeight());
    borderPane.setMinWidth(getWidth());
    borderPane.setMinHeight(getHeight());
    BorderStroke borderStroke = new BorderStroke(ColorPalette.BLACK,
            BorderStrokeStyle.SOLID,
            new CornerRadii(10),
            new BorderWidths(1));
    borderPane.setBorder(new Border(borderStroke));
    Rectangle clip = new Rectangle(getWidth(), 200);
    clip.setArcWidth(20);
    clip.setArcHeight(20);
    borderPane.setClip(clip);
    borderPane.setOnMouseClicked(e -> notifyObservers(ButtonEnum.OPEN_RECIPE));
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

  @Override
  public void addObserver(Observer observer) {
    if (observer != null) {
      if (!observers.contains(observer)) {
        observers.add(observer);
      }
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  @Override
  public void removeObserver(Observer observer) {
    if (observer != null) {
      observers.remove(observer);
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  protected void notifyObservers(ButtonEnum buttonEnum) {
    List<Observer> observersCopy = new ArrayList<>(this.observers);
    for (Observer observer : observersCopy) {
      observer.update(buttonEnum, this.recipe);
    }
  }
}
