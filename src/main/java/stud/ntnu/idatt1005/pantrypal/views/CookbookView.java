package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.views.components.CookbookRecipeComponent;

/**
 * The CookbookView class is responsible for creating and managing the view for the
 * cookbook in the application.
 * It extends the View class and uses a CookbookController to interact with the model.
 * The CookbookView displays a collection of recipes in a grid-like structure.
 * The number of recipes per row and the spacing between them can be adjusted.
 * Each recipe is represented by a CookbookRecipeComponent.
 */
public class CookbookView extends View {
  /**
   * The number of recipes per row in the view.
   */
  private final int amountOfRecipesPerRow;
  /**
   * The spacing between the recipes in the view.
   */
  private final double spacing;
  /**
   * A map containing the recipes to be displayed in the view.
   */
  private final Map<String, Recipe> recipes;
  private final CookBookController controller;

  /**
   * Constructs a CookbookView with a given CookBookController.
   * It initializes the amount of recipes per row, calculates the spacing between them,
   * and retrieves the recipes from the controller.
   * It then creates the view.
   *
   * @param controller the CookBookController that this view interacts with
   */
  public CookbookView(CookBookController controller) {
    super(controller, Route.COOKBOOK, "/styles/cookbook.css");
    this.controller = controller;
    this.setScrollPane();
    amountOfRecipesPerRow = 4;
    spacing = calculateSpacing();
    recipes = controller.getRecipes();
    createView();
  }
  /**
   * Creates the view for the cookbook.
   * It creates a VBox to contain the rows of recipes, and an HBox for each row.
   * It then adds the CookbookRecipeComponents to the rows and the rows to the container.
   */
  private void createView() {
    VBox recipeContainer = new VBox(spacing / 2);
    recipeContainer.setPadding(new Insets(spacing, 0, spacing, 0));
    HBox row = new HBox(spacing);
    for (Recipe recipe : recipes.values()) {
      if (row.getChildren().size() < amountOfRecipesPerRow) {
        row.getChildren().add(new CookbookRecipeComponent(recipe, this.controller).getBorderPane());
      } else {
        row.setAlignment(Pos.CENTER);
        recipeContainer.getChildren().add(row);
        row = new HBox(spacing);
        row.getChildren().add(new CookbookRecipeComponent(recipe, this.controller).getBorderPane());
      }

    }
    row.setAlignment(Pos.CENTER);
    recipeContainer.getChildren().add(row);
    getBorderPane().setCenter(recipeContainer);
  }

  /**
   * Calculates the spacing between the recipes based on the width of the screen,
   * the width of the recipes, and the number of recipes per row.
   *
   * @return the calculated spacing
   */
  private double calculateSpacing() {
    Rectangle2D visualBounds = getPrimary().getVisualBounds();
    return ((visualBounds.getWidth() - amountOfRecipesPerRow * CookbookRecipeComponent.getWidth())
        / (amountOfRecipesPerRow + 1));
  }
}
