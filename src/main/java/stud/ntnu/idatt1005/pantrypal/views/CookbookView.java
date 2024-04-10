package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.utils.Sizing;
import stud.ntnu.idatt1005.pantrypal.views.components.CookbookRecipeComponent;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

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
  private static final int RECIPES_PER_ROW = 4;
  /**
   * The spacing between the recipes in the view.
   */
  private final double spacing;
  /**
   * A map containing the recipes to be displayed in the view.
   */
  private final CookBookController controller;

  private final VBox pageContainer;

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
    this.pageContainer = new VBox();
    spacing = calculateSpacing();
    addSearchBar();
    render();
  }

  private void addSearchBar() {
    TextField searchField = new TextField();
    searchField.setPromptText("Search");
    NodeUtils.addClasses(searchField, "search-field");
    searchField.setMaxWidth(Sizing.getScreenWidth());
    searchField.setMinWidth(Sizing.getScreenWidth());
    NodeUtils.addChildren(pageContainer, searchField);
    searchField.textProperty().addListener((observable, oldValue, newValue) ->
            this.controller.searchRecipes(newValue));
  }

  /**
   * Creates the view for the cookbook.
   * It creates a VBox to contain the rows of recipes, and an HBox for each row.
   * It then adds the CookbookRecipeComponents to the rows and the rows to the container.
   */
  public void render() {
    VBox recipeContainer = new VBox(spacing / 2);
    //StyledButton addRecipe = addRecipe();
    //recipeContainer.getChildren().add(addRecipe);
    recipeContainer.setPadding(new Insets(spacing, 0, spacing, 0));
    HBox row = new HBox(spacing);
    for (Recipe recipe : controller.getCurrentSearch()) {
      if (row.getChildren().size() >= RECIPES_PER_ROW) {
        row.setAlignment(Pos.CENTER);
        recipeContainer.getChildren().add(row);
        row = new HBox(spacing);
      }
      CookbookRecipeComponent recipeComponent = new CookbookRecipeComponent(recipe);
      recipeComponent.addObserver(controller);
      row.getChildren().add(recipeComponent.getComponent());

    }
    row.setAlignment(Pos.CENTER);
    recipeContainer.getChildren().add(row);
    if (pageContainer.getChildren().size() < 2) {
      NodeUtils.addChildren(pageContainer, recipeContainer);
    } else {
      pageContainer.getChildren().set(1, recipeContainer);
    }
    getBorderPane().setCenter(pageContainer);
  }

  private StyledButton addRecipe() {
    StyledButton button = new StyledButton("Add Recipe");
    button.setOnAction(e -> notifyObservers(ButtonEnum.ADD));

    return button;
  }

  /**
   * Calculates the spacing between the recipes based on the width of the screen,
   * the width of the recipes, and the number of recipes per row.
   *
   * @return the calculated spacing
   */
  private double calculateSpacing() {
    Rectangle2D visualBounds = getPrimary().getVisualBounds();
    return ((visualBounds.getWidth() - RECIPES_PER_ROW * CookbookRecipeComponent.getWidth())
        / (RECIPES_PER_ROW + 1));
  }
}
