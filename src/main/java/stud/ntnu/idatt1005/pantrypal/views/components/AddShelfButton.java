package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.views.PantryView;

public class AddShelfButton extends Button {

  public AddShelfButton(PantryView pantryView) {
    super("Add New Shelf");

    // Set the button color to green
    setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

    // Set the button to fill its parent HBox
    setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    // Set the action to add a new ShelfComp to the PantryView
    setOnAction(event -> pantryView.addShelf(new ShelfComp()));
  }
}