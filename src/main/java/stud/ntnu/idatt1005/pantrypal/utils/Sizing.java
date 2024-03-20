package stud.ntnu.idatt1005.pantrypal.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * The Sizing class is a utility class that provides methods
 * for getting the screen size and the size of the recipe box.
 */
public class Sizing {

  /**
   * Constructor for the width of the screen.
   *
   * @return the width of the screen
   */
  public static double getScreenWidth() {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    return primaryScreenBounds.getWidth();
  }

  /**
   * Constructor for the height of the screen.
   *
   * @return the height of the screen
   */
  public static double getScreenHeight() {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    return primaryScreenBounds.getHeight();
  }

  /**
   * Constructor for the size of the recipe box.
   *
   * @return the size of the recipe box
   */
  public static double[] getRecipeBoxSize() {
    double[] measures = new double[2];
    measures[0] = getScreenWidth() * 0.7;
    measures[1] = getScreenHeight() * 0.3;
    return measures;
  }

}
