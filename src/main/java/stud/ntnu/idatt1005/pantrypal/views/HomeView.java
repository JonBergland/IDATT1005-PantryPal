package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.utils.Sizing;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

/**
 * This class represents the HomeView in the application. It extends the View class and sets the
 * scene for the stage. The HomeView is created with the HOME viewType, providing a central
 * view for the applications home screen with the application name at the top.
 * The view contains three sections: "Pantry", "Cook Book" and "Shopping List" with corresponding
 * buttons to navigate to the corresponding views.
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
  private final VBox homeViewCookbookBackground = new VBox();

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
    setCookbookBox();
    setPantryBox();
    setShoppingListBox();
    setLayout();
    applyButtons();
  }

  /**
   * Sets the "Cook Book" section box.
   * The box contains the cookBookText, cookBookSubText and cookBookButton.
   * The box is styled and added to the right side of the view.
   * The box is set to take up 50% of the width and 100% of the height of the view.
   */
  private void setCookbookBox() {
    HBox cookBookBox = new HBox(40);
    cookBookBox.setAlignment(Pos.CENTER);
    homeViewCookbookBackground.getChildren().add(cookBookBox);
    homeViewCookbookBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewCookbookBackground.getStyleClass().add("cookbook-background");
    setBoxSize(homeViewCookbookBackground, 1.0);
    getBorderPane().setLeft(homeViewCookbookBackground);
    VBox cookbookTextBox = createTextBox(cookBookText, cookBookUndertext, cookBookButton);

    ImageView icon = createIcon("images/icons/cookbookIcon.png");
    NodeUtils.addChildren(cookBookBox, cookbookTextBox, icon);
  }

  /**
   * Sets the "Pantry" section box.
   * The box contains the pantryText, pantrySubText and pantryButton.
   * The box is styled and added to the left side of the view.
   * The box is set to take up 50% of the width and 50% of the height of the view.
   */
  private void setPantryBox() {
    HBox pantryBox = new HBox(40);
    pantryBox.setAlignment(Pos.CENTER);
    homeViewPantryBackground.getChildren().add(pantryBox);
    homeViewPantryBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewPantryBackground.getStyleClass().add("pantry-background");
    setBoxSize(homeViewPantryBackground, 0.5);
    VBox pantryTextBox = createTextBox(pantryText, pantryUndertext, pantryButton);

    ImageView icon = createIcon("images/icons/fridgeIcon.png");
    NodeUtils.addChildren(pantryBox, pantryTextBox, icon);
  }

  /**
   * Sets the "Shopping List" section box.
   * The box contains the shoppingListText, shoppingListSubText and shoppingListButton.
   * The box is styled and added to the right side of the view.
   * The box is set to take up 50% of the width and 50% of the height of the view.
   */
  private void setShoppingListBox() {
    HBox shoppingListBox = new HBox(40);
    shoppingListBox.setAlignment(Pos.CENTER);

    homeViewShoppingListBackground.getChildren().add(shoppingListBox);
    homeViewShoppingListBackground.setAlignment(Pos.CENTER_LEFT);
    homeViewShoppingListBackground.getStyleClass().add("shopping-list-background");
    setBoxSize(homeViewShoppingListBackground, 0.5);

    VBox shoppingListTextBox = createTextBox(shoppingListText, shoppingListUndertext,
            shoppingListButton);
    ImageView icon = createIcon("images/icons/shoppingCartIcon.png");
    NodeUtils.addChildren(shoppingListBox, shoppingListTextBox, icon);
  }

  /**
   * Creates a text box with the specified header, subText and button.
   *
   * @param header  The header text for the box.
   * @param subText The subText for the box.
   * @param button  The button for the box.
   * @return The VBox containing the header, subText and button.
   */
  private VBox createTextBox(Text header, Text subText, Button button) {
    header.getStyleClass().add("header-text");
    subText.getStyleClass().add("subheader-text");
    subText.setWrappingWidth(300);
    button.getStyleClass().add("styled-button");
    VBox textBox = new VBox(5);
    NodeUtils.addChildren(textBox, header, subText, button);
    textBox.setAlignment(Pos.TOP_LEFT);
    return textBox;
  }

  /**
   * Creates an icon with the specified path.
   *
   * @param path The path to the icon.
   * @return The ImageView containing the icon.
   */
  private ImageView createIcon(String path) {
    ImageView icon = new ImageView(new Image(path));
    icon.setFitHeight(150);
    icon.setFitWidth(150);
    return icon;
  }

  /**
   * Sets the size of the box based on the width and height factor.
   *
   * @param box          The box to set the size of.
   * @param heightFactor The factor to multiply the height of the box.
   */
  private void setBoxSize(VBox box, double heightFactor) {
    box.setMaxWidth(0.5 * Sizing.getScreenWidth());
    box.setPrefWidth(0.5 * Sizing.getScreenWidth());
    box.setMaxHeight(heightFactor * Sizing.getScreenHeight());
    box.setPrefHeight(heightFactor * Sizing.getScreenHeight());
    VBox.setVgrow(box, Priority.ALWAYS);
  }

  /**
   * Uses the buttons to navigate to the corresponding view.
   */
  private void applyButtons() {
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
  private void createButton(StyledButton button, Runnable action) {
    button.setBackground(new Background(
            new BackgroundFill(Color.BLACK, new CornerRadii(10), Insets.EMPTY)));
    if (action != null) {
      button.setOnAction(event -> action.run());
    }
  }

  /**
   * Sets the layout of the HomeView.
   * The layout contains the "Pantry", "Cook Book" and "Shopping List" sections.
   */
  private void setLayout() {
    VBox rightSide = new VBox(homeViewPantryBackground, homeViewShoppingListBackground);
    getBorderPane().setRight(rightSide);
  }
}
