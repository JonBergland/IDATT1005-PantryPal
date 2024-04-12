package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.utils.Sizing;
import stud.ntnu.idatt1005.pantrypal.views.components.CookbookRecipeComponent;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

import java.util.ArrayList;
import java.util.List;

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
    render(this.controller.getCurrentSearch());
  }

  private void addSearchBar() {
    TextField searchField = createSearchField();
    StyledButton addRecipe = createAddRecipeButton();

    StackPane searchBar = new StackPane();
    searchBar.getChildren().addAll(searchField, addRecipe);

    NodeUtils.addChildren(pageContainer, searchBar);
    searchField.textProperty().addListener((observable, oldValue, newValue) ->
            this.controller.searchRecipes(newValue));
  }

  /**
   * Creates the view for the cookbook.
   * It creates a VBox to contain the rows of recipes, and an HBox for each row.
   * It then adds the CookbookRecipeComponents to the rows and the rows to the container.
   */
  public void render(List<Recipe> currentSearch) {
    VBox recipeContainer = createRecipeContainer(currentSearch);
    if (pageContainer.getChildren().size() < 2) {
      NodeUtils.addChildren(pageContainer, recipeContainer);
    } else {
      pageContainer.getChildren().set(1, recipeContainer);
    }
    getBorderPane().setCenter(pageContainer);
  }

  private VBox createRecipeContainer(List<Recipe> currentSearch) {
    VBox recipeContainer = new VBox(spacing / 2);
    recipeContainer.setPadding(new Insets(spacing, 0, spacing, 0));

    HBox row = new HBox(spacing);
    ArrayList<Recipe> recipes = new ArrayList<>(currentSearch);
    recipes.sort((a, b) -> Boolean.compare(b.getIsFavorite(), a.getIsFavorite()));
    for (Recipe recipe : recipes) {
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
    return recipeContainer;
  }
  private StyledButton createAddRecipeButton() {
    StyledButton button = new StyledButton("Add Recipe");
    button.setOnAction(e -> notifyObservers(ButtonEnum.ADD));
    StackPane.setAlignment(button, Pos.CENTER_RIGHT);

    return button;
  }

  private TextField createSearchField() {
    TextField searchField = new TextField();
    searchField.setPromptText("Search");
    NodeUtils.addClasses(searchField, "search-field");
    searchField.setMaxWidth(Sizing.getScreenWidth());
    searchField.setMinWidth(Sizing.getScreenWidth());
    searchField.textProperty().addListener((observable, oldValue, newValue) ->
            this.controller.searchRecipes(newValue));
    return searchField;
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
