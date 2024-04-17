package stud.ntnu.idatt1005.pantrypal.views;

import java.util.List;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.utils.Sizing;
import stud.ntnu.idatt1005.pantrypal.views.components.AddGroceryListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.GroceryListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledTextArea;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledTextField;

/**
 * The AddRecipeView class is responsible for creating and managing the view
 * for adding a recipe to the cookbook, or modifying an existing one.
 * It extends the View class and uses a AddRecipeController to interact with the models.
 * Goal: Allow the user to add a new recipe or modify an existing one.
 */
public class AddRecipeView extends View {
  private final StyledTextField name;
  private final StyledTextArea description;
  private final StyledTextField imageUrl;

  /**
   * Constructor for AddRecipeView. It initializes the AddRecipeView with an AddRecipeController.
   * This constructor is used when adding a new recipe.
   *
   * @param controller The AddRecipeController associated with this render.
   */
  public AddRecipeView(AddRecipeController controller) {
    super(controller, Route.ADD_RECIPE, "/styles/add-recipe.css");
    this.name = new StyledTextField("Name");
    this.description = createDescription();
    this.imageUrl = new StyledTextField("Image URL");
  }

  /**
   * Constructor for AddRecipeView. It initializes the AddRecipeView with an AddRecipeController.
   * This constructor is used when modifying an existing recipe.
   *
   * @param controller The AddRecipeController associated with this render.
   * @param recipe     The recipe to be modified.
   */
  public AddRecipeView(AddRecipeController controller, Recipe recipe) {
    super(controller, Route.ADD_RECIPE, "/styles/add-recipe.css");
    this.name = new StyledTextField("Name");
    this.description = createDescription();
    this.name.setText(recipe.getKey());
    this.description.setText(recipe.getDescription());
    this.imageUrl = new StyledTextField("Image URL");
    this.imageUrl.setText(recipe.getImagePath());
  }

  /**
   * Renders the view for adding a recipe.
   * The view consists of a form with fields for name, description, ingredients, and steps.
   * The user can add ingredients and steps to the recipe.
   * The user can submit the form to add the recipe to the cookbook.
   *
   * @param groceryRegister The register containing the groceries to be added to the recipe.
   * @param stepRegister    The register containing the steps to be added to the recipe.
   */
  public void render(GroceryRegister groceryRegister, StepRegister stepRegister) {
    // Create the overarching VBox
    VBox form = new VBox();
    form.getStyleClass().add("form");
    form.setAlignment(Pos.CENTER);
    form.setMaxHeight(Sizing.getScreenHeight() * 0.88);

    // Create the scroll pane to hold the form
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    // Create the VBox to hold the form elements
    VBox innerForm = new VBox();

    // Create the title for the form
    Text title = new Text("Add Recipe");
    title.getStyleClass().add("title");
    HBox titleBox = createTitleBox(title);
    titleBox.setAlignment(Pos.TOP_LEFT);

    // Create the grocery list with title and option to add groceries
    Text groceryListTitle = new Text("Ingredients:");
    groceryListTitle.getStyleClass().add("subtitle");
    HBox groceryListTitleBox = createTitleBox(groceryListTitle);
    ScrollPane groceryList = createGroceryList(groceryRegister);

    AddGroceryListElement addGrocery = new AddGroceryListElement("");
    for (Observer observer : observers) {
      addGrocery.addObserver(observer);
    }

    // Create the step list with title and option to add steps
    Text stepListTitle = new Text("Steps:");
    stepListTitle.getStyleClass().add("subtitle");
    HBox stepListTitleBox = createTitleBox(stepListTitle);
    ScrollPane stepList = createStepList(stepRegister.getSteps());

    // Create the button to add a step
    HBox addStep = createAddStepBox();

    // Create a border to separate the form from the button
    Pane border = new Pane();
    border.setMinHeight(10);
    border.setStyle("-fx-border-width: 1 0 0 0; -fx-border-color: #000000;");

    // Create the button to submit the form
    StyledButton submit = new StyledButton("Add Recipe");
    submit.setMaxWidth(Double.MAX_VALUE);
    submit.setOnAction(e -> {
      Recipe recipe = new Recipe(name.getText(), description.getText(),
              groceryRegister, stepRegister, imageUrl.getText(), false);
      notifyObservers(ButtonEnum.ADD, recipe);
    });

    innerForm.getChildren().addAll(titleBox, name, description, imageUrl,
        groceryListTitleBox, groceryList, addGrocery,
        stepListTitleBox, stepList, addStep,
        border, submit);
    scrollPane.setContent(innerForm);
    form.getChildren().add(scrollPane);
    this.getBorderPane().setCenter(form);
  }

  /**
   * Creates a text area for the description of the recipe.
   *
   * @return A styled text area for the description of the recipe.
   */
  private StyledTextArea createDescription() {
    StyledTextArea descriptionField = new StyledTextArea("Description");
    descriptionField.setMinHeight(Sizing.getScreenHeight() * 0.18);
    descriptionField.setMaxHeight(Sizing.getScreenHeight() * 0.18);
    descriptionField.setWrapText(true);
    return descriptionField;
  }

  /**
   * Creates a ScrollPane with the grocery list with the groceries in the grocery register.
   *
   * @param groceryRegister The register containing the groceries to be added to the recipe.
   *
   * @return A scroll pane containing the grocery list.
   */
  private ScrollPane createGroceryList(GroceryRegister groceryRegister) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMinHeight(Sizing.getScreenHeight() * 0.3);
    scrollPane.setMaxHeight(Sizing.getScreenHeight() * 0.3);
    VBox groceryList = new VBox();

    for (Grocery grocery : groceryRegister.getRegister().values()) {
      GroceryListElement element = new GroceryListElement.GroceryListElementBuilder(grocery)
          .text(grocery.getName())
          .text(grocery.getShelf())
          .quantity()
          .build();

      for (Observer observer : observers) {
        element.addObserver(observer);
      }

      element.getPane().setMinWidth(Sizing.getScreenWidth() * 0.6);
      groceryList.getChildren().add(element.getPane());
    }

    scrollPane.setContent(groceryList);
    return scrollPane;
  }

  /**
   * Creates a title box with the given text.
   *
   * @param text The text to be displayed in the title box.
   *
   * @return A title box with the given text.
   */
  private HBox createTitleBox(Text text) {
    HBox titleBox = new HBox();
    Pane fillerPaneLeft = new Pane();
    fillerPaneLeft.setMinWidth(10);

    Pane fillerPaneRight = new Pane();
    HBox.setHgrow(fillerPaneRight, Priority.ALWAYS);
    titleBox.getChildren().addAll(fillerPaneLeft, text, fillerPaneRight);

    return titleBox;
  }

  /**
   * Creates a scroll pane with the steps in the step register.
   *
   * @param stepRegister The register containing the steps to be added to the recipe.
   *
   * @return A scroll pane containing the step list.
   */
  private ScrollPane createStepList(List<String> stepRegister) {
    int elementHeight = 20;

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMinHeight(Sizing.getScreenHeight() * 0.3);
    scrollPane.setMaxHeight(Sizing.getScreenHeight() * 0.3);
    VBox stepList = new VBox();
    stepList.getStyleClass().add("step-list");

    for (int i = 0; i < stepRegister.size(); i++) {
      Text stepNumber = new Text(i + 1 + ".");
      StackPane stepNumberPane = new StackPane(stepNumber);
      stepNumberPane.setAlignment(Pos.CENTER);
      stepNumberPane.setMaxHeight(elementHeight);

      String step = stepRegister.get(i);
      StackPane stepPane = new StackPane(new Text(step));
      stepPane.setAlignment(Pos.CENTER);
      stepPane.setMaxHeight(elementHeight);

      StyledButton removeStep = new StyledButton("X", StyledButton.Variant.DELETE,
              StyledButton.Size.MEDIUM);
      removeStep.setOnAction(e -> notifyObservers(ButtonEnum.REMOVE, step));
      removeStep.setMaxHeight(elementHeight);

      BorderPane stepElement = new BorderPane();
      stepElement.getStyleClass().add("step-element");
      stepElement.setLeft(stepNumberPane);
      stepElement.setCenter(stepPane);
      stepElement.setRight(removeStep);
      stepElement.setMaxHeight(elementHeight);

      stepList.getChildren().add(stepElement);
    }

    scrollPane.setContent(stepList);
    return scrollPane;
  }

  /**
   * Creates a box with a text field for adding a step to the recipe.
   *
   * @return A box with a text field for adding a step to the recipe.
   */
  private HBox createAddStepBox() {
    HBox addStepBox = new HBox();
    addStepBox.getStylesheets().add(Objects.requireNonNull(getClass()
            .getResource("/styles/pantry.css")).toExternalForm());
    addStepBox.setAlignment(Pos.CENTER);
    NodeUtils.addClasses(addStepBox, "add-grocery-container");

    StyledTextField stepField = new StyledTextField("Step");
    addStepBox.getChildren().add(stepField);


    StyledButton addStep = new StyledButton("Add Step");
    addStep.setMaxWidth(Double.MAX_VALUE);
    addStep.setOnAction(e -> {
      if (!stepField.getText().isEmpty()) {
        notifyObservers(ButtonEnum.ADD, stepField.getText());
        stepField.clear();
      }
    });

    addStepBox.getChildren().add(addStep);
    return addStepBox;
  }

  /**
   * Notifies the observers of the button that was pressed and the object affected.
   *
   * @param buttonEnum The button that was pressed.
   * @param recipe The Recipe-object that was pressed.
   */
  private void notifyObservers(ButtonEnum buttonEnum, Recipe recipe) {
    for (Observer observer : observers) {
      observer.update(buttonEnum, recipe);
    }
  }

  /**
   * Notifies the observers of the button that was pressed and the object affected.
   *
   * @param buttonEnum The button that was pressed.
   * @param step The step that was added
   */
  private void notifyObservers(ButtonEnum buttonEnum, String step) {
    for (Observer observer : observers) {
      observer.update(buttonEnum, step);
    }
  }
}