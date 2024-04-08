package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.Sizing;
import stud.ntnu.idatt1005.pantrypal.views.components.FavoriteButton;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the RecipeView in the application. It extends the View class and sets the
 * scene for the stage. The RecipeView is responsible for displaying a single recipe in
 * the application.
 * It includes the recipe overview, steps, and groceries.
 */
public class RecipeView extends View implements Observable {

  /**
   * The recipe to be displayed in the view.
   */
  private final Recipe recipe;

  /**
   * Constructor for RecipeView.
   *
   * @param controller The controller for the view.
   * @param recipe     The recipe to be displayed in the view.
   */
  public RecipeView(CookBookController controller, Recipe recipe) {
    super(controller, Route.RECIPE, "/styles/recipe.css");
    this.recipe = recipe;
    setUpView();
  }

  /**
   * Sets up the view for the recipe.
   * Includes the recipe overview, steps, and groceries.
   */
  private void setUpView() {
    VBox recipeContainer = new VBox(20);
    recipeContainer.setPadding(new Insets(20));
    recipeContainer.getStyleClass().add("recipe-container");
    recipeContainer.setMaxWidth(getPrimary().getVisualBounds().getWidth() * 0.7);
    recipeContainer.getChildren().addAll(
            createRecipeOverview(),
            createSeparator(Sizing.getRecipeBoxSize()[0]),
            setUpRecipeStepsAndGroceries()
    );
    recipeContainer.setAlignment(Pos.TOP_CENTER);
    getBorderPane().setCenter(recipeContainer);
  }

  /**
   * Creates the recipe overview. That is, the image, name, description, and buttons
   * for adding groceries to shopping list and to favorite the recipe.
   *
   * @return The recipe overview
   */
  public HBox createRecipeOverview() {
    HBox recipeOverview = new HBox(80);
    recipeOverview.setMaxSize(Sizing.getRecipeBoxSize()[0], Sizing.getRecipeBoxSize()[1]);

    Rectangle image = new Rectangle(Sizing.getRecipeBoxSize()[0] * 0.4,
            Sizing.getRecipeBoxSize()[1]);
    String imagePath = recipe.getImagePath();
    if (imagePath != null && !recipe.getImagePath().isEmpty()) {
      ImagePattern imagePattern = new ImagePattern(new Image(imagePath));
      image.setFill(imagePattern);
    } else {
      image.setFill(ColorPalette.GRAY);
    }

    recipeOverview.getChildren().addAll(
            image,
            setUpOverviewText()
    );
    recipeOverview.setAlignment(Pos.TOP_CENTER);
    return recipeOverview;
  }

  /**
   * Sets up the text-part of the recipe overview. To get an overview of the recipe.
   * Includes the recipe name, description, and buttons for adding groceries and to
   * favorite the recipe.
   *
   * @return The overview text
   */
  private VBox setUpOverviewText() {
    VBox textContainer = new VBox(20);
    textContainer.setMinHeight(Sizing.getRecipeBoxSize()[1]);
    Text header = new Text(this.recipe.getKey());
    header.setFont(new Font("Times new roman", 60));

    Text description = new Text("Recipe description - Lorem ipsum dolor sit amet, "
            + "consectetur adipiscing elit. Morbi malesuada nulla diam, quis vulputate augue "
            + "porta sed. Sed semper neque ac tempus molestie. Suspendisse ultricies erat nunc, "
            + "ut fringilla leo porta id. Vivamus euismod fringilla leo.");
    description.setWrappingWidth(400);

    textContainer.getChildren().addAll(header, description, createOverviewButtons());
    textContainer.setAlignment(Pos.TOP_LEFT);
    return textContainer;
  }

  /**
   * Creates the buttons for the recipe overview.
   * The buttons are used to add groceries to the shopping list and to favorite the recipe.
   *
   * @return The overview buttons
   */
  private HBox createOverviewButtons() {
    StyledButton addNeededGroceriesButton = new StyledButton("Add groceries",
            StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);
    addNeededGroceriesButton.getStyleClass().add("overview-buttons");
    addNeededGroceriesButton.setOnAction(e -> notifyObservers(ButtonEnum.ADD_TO_SHOPPING_LIST));

    FavoriteButton favoriteButton = new FavoriteButton(recipe.getIsFavorite());
    favoriteButton.setOnMouseClicked(e -> {
      notifyObservers(ButtonEnum.EDIT);
      favoriteButton.toggleStarColor();
    });

    HBox overviewButtons = new HBox(20);
    overviewButtons.getChildren().addAll(
            addNeededGroceriesButton,
            favoriteButton);
    overviewButtons.setAlignment(Pos.CENTER_LEFT);
    overviewButtons.setPadding(new Insets(0, 0, 0, 0));
    return overviewButtons;
  }

  /**
   * Creates a separator.
   *
   * @param size The size of the separator
   * @return The separator
   */
  private Rectangle createSeparator(double size) {
    Rectangle separator = new Rectangle();
    separator.setWidth(size);
    separator.setHeight(1);
    separator.setFill(ColorPalette.BLACK);

    return separator;
  }

  /**
   * Sets up the recipe steps and groceries section of the view.
   * The section contains the steps and groceries needed for the recipe. And
   * displays them in at the bottom of the view.
   *
   * @return The container with steps and groceries
   */
  private HBox setUpRecipeStepsAndGroceries() {
    HBox stepsAndGroceries = new HBox();
    stepsAndGroceries.setMinWidth(Sizing.getRecipeBoxSize()[0]);
    VBox stepsContainer = setUpRecipeSteps();
    VBox groceriesContainer = setUpRecipeGroceries();
    HBox.setHgrow(stepsContainer, Priority.ALWAYS);
    HBox.setHgrow(groceriesContainer, Priority.ALWAYS);
    stepsAndGroceries.getChildren().addAll(
            stepsContainer,
            groceriesContainer
    );
    return stepsAndGroceries;
  }

  /**
   * Sets up the recipe steps section.
   *
   * @return The container with steps
   */
  private VBox setUpRecipeSteps() {
    VBox stepsContainer = new VBox(20);
    stepsContainer.getStyleClass().add("steps-container");
    HBox numberedStep;
    for (String step : recipe.getRecipeSteps()) {
      Text stepText = new Text(step);
      stepText.setFont(new Font(25));
      numberedStep = new HBox(20);
      numberedStep.setAlignment(Pos.CENTER_LEFT);
      numberedStep.getChildren().addAll(
              setUpStepNumber(recipe.getRecipeSteps().indexOf(step) + 1),
              stepText
      );
      stepsContainer.getChildren().add(numberedStep);
    }
    return stepsContainer;
  }

  /**
   * Sets up the step number component for a step.
   * The step number component is a circle with a number inside.
   *
   * @param number The number of the step
   * @return The step number container
   */
  private StackPane setUpStepNumber(int number) {
    Circle stepNumber = new Circle(25);
    stepNumber.setFill(ColorPalette.GRAY);
    Text stepNumberText = new Text(String.valueOf(number));
    stepNumberText.setFont(new Font(25));
    StackPane stepNumberContainer = new StackPane();
    stepNumberContainer.getChildren().addAll(stepNumber, stepNumberText);
    return stepNumberContainer;
  }

  /**
   * Sets up the recipe groceries section.
   * The section contains the groceries needed for the recipe.
   *
   * @return The container with groceries
   */
  private VBox setUpRecipeGroceries() {
    VBox groceriesContainer = new VBox(20);
    groceriesContainer.setMaxWidth(Sizing.getRecipeBoxSize()[0] * 0.3);

    Text groceriesHeader = new Text("Ingredients:");
    groceriesContainer.getChildren().add(groceriesHeader);

    groceriesHeader.getStyleClass().add("groceries-header");

    for (Grocery grocery : recipe.getRecipeGroceries().getRegister().values()) {
      groceriesContainer.getChildren().add(createGroceryTextWithSeparator(grocery));
    }
    groceriesContainer.setAlignment(Pos.TOP_LEFT);
    return groceriesContainer;
  }

  /**
   * Creates a grocery text with a separator. To be used in the groceries section.
   *
   * @param grocery The grocery to be displayed
   * @return The grocery text with a separator
   */
  private VBox createGroceryTextWithSeparator(Grocery grocery) {
    HBox groceryText = new HBox();
    groceryText.getStyleClass().add("grocery-text");

    Text nameText = new Text(grocery.getKey());
    nameText.getStyleClass().add("sub-groceries-text");

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    Text quantityText = new Text(grocery.getQuantity() + " pcs.");
    quantityText.getStyleClass().add("sub-groceries-text");
    quantityText.setTextAlignment(TextAlignment.RIGHT);

    groceryText.getChildren().addAll(
            nameText,
            spacer,
            quantityText
    );

    VBox groceryTextWithSeparator = new VBox(10);
    groceryTextWithSeparator.getChildren().addAll(
            groceryText,
            createSeparator(Sizing.getRecipeBoxSize()[0] * 0.3)
    );

    return groceryTextWithSeparator;
  }

  /**
   * Updates the observer based on the button pressed.
   *
   * @param buttonEnum the button that was pressed
   */
  @Override
  protected void notifyObservers(ButtonEnum buttonEnum) {
    List<Observer> observersCopy = new ArrayList<>(this.observers);
    for (Observer observer : observersCopy) {
      observer.update(buttonEnum, this.recipe);
    }
  }
}
