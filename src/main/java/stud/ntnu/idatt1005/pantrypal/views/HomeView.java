package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

/**
 * This class represents the HomeView in the application. It extends the View class and sets the
 * scene for the stage. The HomeView is created with the HOME viewType, providing a central
 * view for the applications home screen.
 * This class is associated with an HomeController to handle the logic and actions related to the
 * home screen.
 */
public class HomeView extends View {

  private final HomeController controller;

  private final Text pantryText = new Text("Pantry");
  private final Text cookBookText = new Text("Cook Book");
  private final Text shoppingListText = new Text("Shopping List");
  private final Text pantryUndertext = new Text("Check out what you have, or update it.");
  private final Text cookBookUndertext = new Text("Browse different recipes and auto generate shopping list.");
  private final Text shoppingListUndertext = new Text("Start generating a shopping list for your next trip to the store.");

  private final VBox homeViewPantryBackground = new VBox();
  private final VBox homeViewRecipeBackground = new VBox();
  private final VBox homeViewShoppingListBackground = new VBox();

  private final StyledButton pantryButton = new StyledButton("Pantry", StyledButton.Variant.SOLID, StyledButton.Size.LARGE);
  private final StyledButton cookBookButton = new StyledButton("Cook Book", StyledButton.Variant.SOLID, StyledButton.Size.LARGE);
  private final StyledButton shoppingListButton = new StyledButton("Shopping List", StyledButton.Variant.SOLID, StyledButton.Size.LARGE);
  private final Rectangle2D primaryScreenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
  /**
   * Constructor for HomeView.
   *
   * @param controller The controller for the view.
   */
  public HomeView(HomeController controller) {
    super(controller, Route.HOME);
    this.controller = controller;
    initializeHomeView();
  }

  private void initializeHomeView() {
    setPantryBox();
    setRecipeBox();
    setShoppingListBox();
    setLayout();
    styleHeaderText();
    styleUnderText();
    styleButtons();

  }

  private void setPantryBox() {
    VBox pantryBox = new VBox(pantryText, pantryUndertext, pantryButton);
    pantryBox.setAlignment(Pos.TOP_LEFT);
    pantryBox.setPadding(new Insets(0, 0, 0, 60));
    pantryBox.setSpacing(5);
    homeViewPantryBackground.getChildren().add(pantryBox);
    homeViewPantryBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewPantryBackground.setStyle("-fx-background-color:#9ACD32;");
    setBoxSize(homeViewPantryBackground, 0.5, 1.0);
    root.setLeft(homeViewPantryBackground);
  }

  private void setRecipeBox() {
    VBox recipeBox = new VBox(cookBookText, cookBookUndertext, cookBookButton);
    recipeBox.setAlignment(Pos.TOP_LEFT);
    recipeBox.setPadding(new Insets(0, 0, 0, 60));
    recipeBox.setSpacing(5);
    homeViewRecipeBackground.getChildren().add(recipeBox);
    homeViewRecipeBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewRecipeBackground.setStyle("-fx-background-color: #DDEEDF;");
    setBoxSize(homeViewRecipeBackground, 0.5, 0.5);
  }

  private void setShoppingListBox() {
    VBox shoppingListBox = new VBox(shoppingListText, shoppingListUndertext, shoppingListButton);
    shoppingListBox.setAlignment(Pos.TOP_LEFT);
    shoppingListBox.setPadding(new Insets(0, 0, 0, 60));
    shoppingListBox.setSpacing(5);
    homeViewShoppingListBackground.getChildren().add(shoppingListBox);
    homeViewShoppingListBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewShoppingListBackground.setStyle("-fx-background-color: #FFFFFF;");
    setBoxSize(homeViewShoppingListBackground, 0.5, 0.5);
  }

  private void setBoxSize(VBox box, double widthFactor, double heightFactor) {
    box.setMaxWidth(widthFactor * primaryScreenBounds.getWidth());
    box.setPrefWidth(widthFactor * primaryScreenBounds.getWidth());
    box.setMaxHeight(heightFactor * primaryScreenBounds.getHeight());
    box.setPrefHeight(heightFactor * primaryScreenBounds.getHeight());
    VBox.setVgrow(box, Priority.ALWAYS);
  }

  private void styleHeaderText(){
    applyHeaderText(pantryText);
    applyHeaderText(cookBookText);
    applyHeaderText(shoppingListText);
  }

  private void styleUnderText(){
    applySubHeaderText(pantryUndertext);
    applySubHeaderText(cookBookUndertext);
    applySubHeaderText(shoppingListUndertext);
  }

  private void styleButtons() {
    applyButtonStyle(pantryButton);
    applyButtonStyle(cookBookButton);
    applyButtonStyle(shoppingListButton);
  }

  private void applyHeaderText(Text headerText) {
    headerText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
    headerText.setFill(Color.BLACK);
    headerText.setTextAlignment(TextAlignment.LEFT);
  }

  private void applySubHeaderText(Text subHeaderText) {
    subHeaderText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 25));
    subHeaderText.setFill(Color.BLACK);
    subHeaderText.setTextAlignment(TextAlignment.LEFT);
    subHeaderText.setWrappingWidth(300);
  }

  private void applyButtonStyle(StyledButton button) {
    button.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    button.setTextFill(Color.WHITE);
    button.setPrefWidth(200);
  }


  private void setLayout() {
    VBox rightSide = new VBox(homeViewRecipeBackground, homeViewShoppingListBackground);
    root.setRight(rightSide);
  }
}
