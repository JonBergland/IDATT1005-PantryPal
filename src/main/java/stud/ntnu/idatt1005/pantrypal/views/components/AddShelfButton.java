package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.scene.control.Button;

public class AddShelfButton extends Button {
    public AddShelfButton() {
            super("Add new shelf");
            this.getStyleClass().add("add-shelf-button");
        }
}