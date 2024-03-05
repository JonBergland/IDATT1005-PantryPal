package stud.ntnu.idatt1005.pantrypal.views.components;


import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.shape.Rectangle;



public class NavBar extends Control {
  ToolBar navBar = new ToolBar();
  Button homeButton = new Button("Home");
  Button pantryButton = new Button("Pantry");
  Button shoppingListButton = new Button("Shopping List");
  Button recipesButton = new Button("Recipes");
  Button login = new Button("Login");

  Rectangle separator = new Rectangle(2, 38);
  Label spacing = new Label("                                                                              ");
  Label spacing2= new Label(" ");


  public NavBar() {

    homeButton.setStyle("-fx-background-color: #FFFFF; -fx-text-fill:black;");
    pantryButton.setStyle("-fx-background-color: #FFFFF; -fx-text-fill: black; ");
    shoppingListButton.setStyle("-fx-background-color: #FFFFF; -fx-text-fill: black;");
    recipesButton.setStyle("-fx-background-color: #FFFFF; -fx-text-fill: black;");
    login.setStyle("-fx-background-color: #FFFFF; -fx-text-fill: black;");

    navBar.setMinHeight(Control.USE_PREF_SIZE);
    navBar.setMaxHeight(30);

    navBar.setStyle("-fx-background-color: #FFFFF;-fx-spacing:30;-fx-border-color:#000000;");
    navBar.getItems().addAll(spacing2, homeButton, pantryButton, shoppingListButton, recipesButton, spacing, separator, login);
  }

  public ToolBar getNavBar() {
    return navBar;
  }


}
