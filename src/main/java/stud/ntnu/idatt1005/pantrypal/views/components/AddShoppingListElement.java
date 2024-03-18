package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class AddShoppingListElement extends HBox {
    public AddShoppingListElement() {
        super();
        this.getStyleClass().add("add-shopping-list-element");
        this.setAlignment(Pos.CENTER);

        StyledTextField name = new StyledTextField("Name");
        StyledTextField category = new StyledTextField("Category");
        StyledTextField quantity = new StyledTextField("Quantity");
        StyledButton add = new StyledButton("Add");
        add.setOnAction(e -> {
            name.clear();
            category.clear();
            quantity.clear();
        });

        this.getChildren().addAll(name, category, quantity, add);
    }
}
