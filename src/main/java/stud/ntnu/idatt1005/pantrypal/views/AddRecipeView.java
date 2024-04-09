package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;
import stud.ntnu.idatt1005.pantrypal.registers.GroceryRegister;
import stud.ntnu.idatt1005.pantrypal.registers.StepRegister;
import stud.ntnu.idatt1005.pantrypal.utils.Sizing;
import stud.ntnu.idatt1005.pantrypal.views.components.*;

/**
 * A class that extends View and creates a render for adding a recipe.
 * The goal is to provide a dedicated render for users to add a new recipe.
 * This class is associated with an AddRecipeController to handle the logic
 * and actions related to adding a recipe.
 */
public class AddRecipeView extends View {

  /**
   * Constructor for AddRecipeView.
   *
   * @param controller The AddRecipeController associated with this render.
   */
  public AddRecipeView(AddRecipeController controller) {
    super(controller, Route.ADD_RECIPE, "/styles/add-recipe.css");
  }

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

    // Create the text field for the name
    StyledTextField name = createName();

    // Create the text area for the description
    StyledTextArea description = createDescription();

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
    ScrollPane stepList = createStepList(stepRegister);

    // Create the button to add a step
    StyledButton addStep = createAddStepButton();


    // Create the button to submit the form
    StyledButton submit = new StyledButton("Add Recipe");
    submit.setMaxWidth(Double.MAX_VALUE);
    submit.setOnAction(e -> {
      //Recipe recipe = new Recipe(name.getText(), description.getText());
      //recipe.addIngredient(ingredient.getGrocery());
      //notifyObservers(ButtonEnum.ADD, recipe);
    });
    Pane filler = createFiller();

    innerForm.getChildren().addAll(titleBox,name, description,
        groceryListTitleBox, groceryList, addGrocery, stepListTitleBox, stepList, submit, filler);
    scrollPane.setContent(innerForm);
    form.getChildren().add(scrollPane);
    this.getBorderPane().setCenter(form);
  }

  private StyledTextField createName() {
    StyledTextField nameField = new StyledTextField("Name");
    return nameField;
  }

  private StyledTextArea createDescription() {
    StyledTextArea descriptionField = new StyledTextArea("Description");
    //descriptionField.setMinHeight(Sizing.getScreenWidth() * 0.18);
    descriptionField.setMaxHeight(Sizing.getScreenHeight() * 0.18);
    descriptionField.setMaxWidth(Sizing.getScreenWidth() * 0.6);
    descriptionField.setWrapText(true);
    return descriptionField;
  }

  private ScrollPane createGroceryList(GroceryRegister groceryRegister) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMaxHeight(Sizing.getScreenHeight() * 0.2);
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

  private HBox createTitleBox(Text text) {
    HBox titleBox = new HBox();
    Pane fillerPaneLeft = new Pane();
    fillerPaneLeft.setMinWidth(10);

    Pane fillerPaneRight = new Pane();
    HBox.setHgrow(fillerPaneRight, Priority.ALWAYS);
    titleBox.getChildren().addAll(fillerPaneLeft, text, fillerPaneRight);

    return titleBox;
  }

  private ScrollPane createStepList(StepRegister stepRegister) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    //scrollPane.setMinHeight(Sizing.getScreenWidth() * 0.2);
    scrollPane.setMaxHeight(Sizing.getScreenHeight() * 0.2);
    VBox stepList = new VBox();
    stepList.getStyleClass().add("step-list");

    for (int i = 0; i < stepRegister.getSteps().size(); i++) {
      Text stepNumber = new Text(i + 1 + ".");
      StackPane stepNumberPane = new StackPane(stepNumber);
      stepNumberPane.setAlignment(Pos.CENTER);

      String step = stepRegister.getSteps().get(i);

      StyledButton removeStep = new StyledButton("X", StyledButton.Variant.DELETE, StyledButton.Size.MEDIUM);
      removeStep.setOnAction(e -> notifyObservers(ButtonEnum.REMOVE, step));

      BorderPane stepElement = new BorderPane();
      stepElement.getStyleClass().add("step-element");
      stepElement.setLeft(stepNumberPane);
      stepElement.setCenter(new Text(step));
      stepElement.setRight(removeStep);

      stepList.getChildren().add(stepElement);
    }

    scrollPane.setContent(stepList);
    return scrollPane;
  }

  private StyledButton createAddStepButton(){
    StyledButton addStep = new StyledButton("Add Step");
    addStep.setMaxWidth(Double.MAX_VALUE);
    addStep.setOnAction(e -> {
      notifyObservers(ButtonEnum.ADD, "Step");
    });
    return addStep;
  }

  private Pane createFiller() {
    Pane filler = new Pane();
    VBox.setVgrow(filler, Priority.ALWAYS);
    return filler;
  }

  private void notifyObservers(ButtonEnum buttonEnum, Recipe recipe) {
    for (Observer observer : observers) {
      observer.update(buttonEnum, recipe);
    }
  }

  private void notifyObservers(ButtonEnum buttonEnum, String step) {
    for (Observer observer : observers) {
      observer.update(buttonEnum, step);
    }
  }
}
