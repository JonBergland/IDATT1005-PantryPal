package stud.ntnu.idatt1005.pantrypal.models;

import java.io.File;
import java.net.URI;
import java.util.List;
import javax.imageio.ImageIO;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;


/**
 * This is an entity class representing a recipe.
 * It contains the name, groceries and steps of the recipe.
 * Goal: act as a model for a recipe.
 */
public class Recipe extends Model {

  /**
   * The recipe description of the recipe.
   */
  private final String description;

  /**
   * The groceries needed for the recipe.
   */
  private final GroceryRegister recipeGroceries;

  /**
   * The steps needed to make the recipe.
   */
  private final StepRegister steps;

  /**
   * The imagePath pointing to image location.
   */
  private final String imagePath;
  /**
   * The boolean isFavorite, which is true if the recipe is a favorite, and false if it is not.
   */
  private boolean isFavorite;

  /**
   * Constructor for the Recipe class.
   *
   * @param name            the name of the recipe.
   * @param recipeGroceries the groceries needed for the recipe.
   * @param steps           the steps needed to make the recipe.
   */
  public Recipe(String name, String description, GroceryRegister recipeGroceries,
                StepRegister steps, String imagePath, boolean isFavorite) {
    super(name);
    this.description = description;
    this.recipeGroceries = recipeGroceries;
    this.steps = steps;
    this.isFavorite = isFavorite;
    if (validImagePath(imagePath)) {
      this.imagePath = imagePath;
    } else {
      this.imagePath = null;
    }
  }

  /**
   * Check if the image path is valid. By checking if the image path is a URL and then if the
   * image path is correctly formatted. returns based on the result.
   *
   * @param imagePath the image path to check.
   * @return true if the image path is valid, false if it is not.
   */
  private static boolean validImagePath(String imagePath) {
    try {
      if (imagePath.matches("^(https?|ftp)://.*(\\.(png|jpg|jpeg|gif|bmp)).*$")) {
        ImageIO.read(new URI(imagePath).toURL());
        return true;
      } else {
        File file = new File(imagePath);
        if (file.exists() && file.isFile() && imagePath.matches(".*\\.(png|jpg|jpeg|gif|bmp)$")) {
          ImageIO.read(file);
          return true;
        }
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Get the description of the recipe.
   *
   * @return the description of the recipe.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the groceries needed for the recipe.
   *
   * @return the groceries needed for the recipe.
   */
  public GroceryRegister getRecipeGroceries() {
    return recipeGroceries;
  }

  /**
   * Get the steps needed to make the recipe.
   *
   * @return the steps needed to make the recipe.
   */
  public List<String> getRecipeSteps() {
    return steps.getSteps();
  }

  /**
   * Get the image path of the recipe.
   *
   * @return the image path of the recipe.
   */
  public String getImagePath() {
    return imagePath;
  }

  /**
   * Get the isFavorite boolean of the recipe.
   *
   * @return the isFavorite boolean of the recipe.
   */
  public boolean getIsFavorite() {
    return isFavorite;
  }

  /**
   * Toggle the isFavorite boolean to the opposite. If isFavorite is true, it will be set to false,
   * and if isFavorite is false, it will be set to true.
   */
  public void toggleIsFavorite() {
    isFavorite = !isFavorite;
  }
}
