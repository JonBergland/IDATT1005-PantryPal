package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.views.components.NavLink;
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

  // Text for the "Pantry" section header.
  private final Text pantryText = new Text("Pantry");

  // Text for the "Cook Book" section header.
  private final Text cookBookText = new Text("Cook Book");

  // Text for the "Shopping List" section header.
  private final Text shoppingListText = new Text("Shopping List");

  // Text for the "Pantry" section subheader.
  private final Text pantryUndertext = new Text("Check out what you have, or update it.");

  // Text for the "Cook Book" section subheader.
  private final Text cookBookUndertext = new Text(
      "Browse different recipes and auto generate shopping list.");

  // Text for the "Shopping List" section subheader.
  private final Text shoppingListUndertext = new Text(
      "Start generating a shopping list for your next trip to the store.");


  // Background for the "Pantry" section.
  private final VBox homeViewPantryBackground = new VBox();

  // Background for the "Cook Book" section.
  private final VBox homeViewCookBookBackground = new VBox();

  // Background for the "Shopping List" section.
  private final VBox homeViewShoppingListBackground = new VBox();

  // Button for the "Pantry" section.
  private final StyledButton pantryButton = new StyledButton(
      "Pantry", StyledButton.Variant.BLACK, StyledButton.Size.LARGE);

  // Button for the "Cook Book" section.
  private final StyledButton cookBookButton = new StyledButton(
      "Cook Book", StyledButton.Variant.BLACK, StyledButton.Size.LARGE);

  // Button for the "Shopping List" section.
  private final StyledButton shoppingListButton = new StyledButton(
      "Shopping List", StyledButton.Variant.BLACK, StyledButton.Size.LARGE);

  // The primary screen bounds.
  private final Rectangle2D primaryScreenBounds =
      javafx.stage.Screen.getPrimary().getVisualBounds();

  /**
   * Constructor for HomeView.
   * Initializes the view and sets the layout and styling.
   *
   * @param controller The HomeController associated with this view.
   */
  public HomeView(HomeController controller) {
    super(controller, Route.HOME, "/styles/home.css");
    this.controller = controller;
    initializeHomeView();
  }

  /**
   * Initializes the HomeView by setting up the layout and styling.
   */
  private void initializeHomeView() {
    setPantryBox();
    setCookBookBox();
    setShoppingListBox();
    setLayout();
    applyHeaderText();
    applySubHeaderText();
    applyButtons();
  }

  /**
   * Sets the "Pantry" section box.
   * The box contains the pantryText, pantryUndertext and pantryButton.
   * The box is styled and added to the left side of the view.
   * The box is set to take up 50% of the width and 100% of the height of the view.
   */
  private void setPantryBox() {
    VBox pantryBox = new VBox(pantryText, pantryUndertext, pantryButton);
    pantryBox.setAlignment(Pos.TOP_LEFT);
    pantryBox.setPadding(new Insets(0, 0, 0, 60));
    pantryBox.setSpacing(5);
    pantryUndertext.setWrappingWidth(300);
    homeViewPantryBackground.getChildren().add(pantryBox);
    homeViewPantryBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewPantryBackground.getStyleClass().add("pantry-background");
    setBoxSize(homeViewPantryBackground, 0.5, 1.0);
    getBorderPane().setLeft(homeViewPantryBackground);
  }

  /**
   * Sets the "Cook Book" section box.
   * The box contains the cookBookText, cookBookUndertext and cookBookButton.
   * The box is styled and added to the right side of the view.
   * The box is set to take up 50% of the width and 50% of the height of the view.
   */
  private void setCookBookBox() {
    VBox recipeBox = new VBox(cookBookText, cookBookUndertext, cookBookButton);
    recipeBox.setAlignment(Pos.TOP_LEFT);
    recipeBox.setPadding(new Insets(0, 0, 0, 60));
    recipeBox.setSpacing(5);
    cookBookUndertext.setWrappingWidth(300);
    homeViewCookBookBackground.getChildren().add(recipeBox);
    homeViewCookBookBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewCookBookBackground.getStyleClass().add("cookbook-background");
    setBoxSize(homeViewCookBookBackground, 0.5, 0.5);
  }

  /**
   * Sets the "Shopping List" section box.
   * The box contains the shoppingListText, shoppingListUndertext and shoppingListButton.
   * The box is styled and added to the right side of the view.
   * The box is set to take up 50% of the width and 50% of the height of the view.
   */
  private void setShoppingListBox() {
    VBox shoppingListBox = new VBox(shoppingListText, shoppingListUndertext, shoppingListButton);
    shoppingListBox.setAlignment(Pos.TOP_LEFT);
    shoppingListBox.setPadding(new Insets(0, 0, 0, 60));
    shoppingListBox.setSpacing(5);
    shoppingListUndertext.setWrappingWidth(300);
    homeViewShoppingListBackground.getChildren().add(shoppingListBox);
    homeViewShoppingListBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewShoppingListBackground.getStyleClass().add("shopping-list-background");
    setBoxSize(homeViewShoppingListBackground, 0.5, 0.5);
  }

  /**
   * Sets the size of the box based on the width and height factor.
   *
   * @param box The box to set the size of.
   * @param widthFactor The factor to multiply the width of the box with.
   * @param heightFactor The factor to multiply the height of the box with.
   */
  private void setBoxSize(VBox box, double widthFactor, double heightFactor) {
    box.setMaxWidth(widthFactor * primaryScreenBounds.getWidth());
    box.setPrefWidth(widthFactor * primaryScreenBounds.getWidth());
    box.setMaxHeight(heightFactor * primaryScreenBounds.getHeight());
    box.setPrefHeight(heightFactor * primaryScreenBounds.getHeight());
    VBox.setVgrow(box, Priority.ALWAYS);
  }

  /**
   * Styles the header text for all the sections.
   */
  private void applyHeaderText() {

    pantryText.getStyleClass().add("header-text");
    cookBookText.getStyleClass().add("header-text");
    shoppingListText.getStyleClass().add("header-text");
  }

  /**
   * Styles the subheader text for all the sections.
   */
  private void applySubHeaderText() {
    pantryUndertext.getStyleClass().add("subheader-text");
    cookBookUndertext.getStyleClass().add("subheader-text");
    shoppingListUndertext.getStyleClass().add("subheader-text");
  }

  /**
   * Uses the buttons to navigate to the corresponding view.
   */
  private void applyButtons() {
    pantryButton.getStyleClass().add("styled-button");
    cookBookButton.getStyleClass().add("styled-button");
    shoppingListButton.getStyleClass().add("styled-button");
    createButton(pantryButton, () -> controller.onNavLinkPress(Route.PANTRY));
    createButton(cookBookButton, () -> controller.onNavLinkPress(Route.COOKBOOK));
    createButton(shoppingListButton, () -> controller.onNavLinkPress(Route.SHOPPING_LIST));
  }




  /**
   * Creates a button with the specified text and action.
   *
   * @param button The button to create.
   * @param action The action to be executed on button click.
   */
  private NavLink createButton(StyledButton button, Runnable action) {
    button.setBackground(new Background(
        new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    NavLink link = new NavLink(button.getText());
    if (action != null) {
      button.setOnAction(event -> action.run());
    }
    return link;
  }

  /**
   * Sets the layout of the HomeView.
   * The layout contains the "Pantry", "Cook Book" and "Shopping List" sections.
   */
  private void setLayout() {
    VBox rightSide = new VBox(homeViewCookBookBackground, homeViewShoppingListBackground);
    getBorderPane().setRight(rightSide);
  }
}
