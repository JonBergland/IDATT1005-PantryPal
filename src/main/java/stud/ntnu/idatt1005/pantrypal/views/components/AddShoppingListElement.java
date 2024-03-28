package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;

public class AddShoppingListElement extends HBox implements Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Constructor for the add shopping list element
     */
    public AddShoppingListElement() {
        super();
        this.getStyleClass().add("add-shopping-list-element");
        this.setAlignment(Pos.CENTER);

        StyledTextField name = new StyledTextField("Name");
        StyledTextField shelf = new StyledTextField("Shelf");
        StyledTextField quantity = new StyledTextField("Quantity");
        StyledButton add = new StyledButton("Add", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

        name.setMaxWidth(200);
        shelf.setMaxWidth(200);
        quantity.setMaxWidth(100);

        // Set the action for the add button
        add.setOnAction(e -> {
            if (!name.getText().isEmpty() && !shelf.getText().isEmpty()) {
                if (quantity.getText().isEmpty()) {
                    quantity.setText("1");
                }
                notifyObservers(ButtonEnum.ADD_TO_SHOPPING_LIST, name.getText(), Integer.parseInt(quantity.getText()), shelf.getText(), false);
                name.clear();
                shelf.clear();
                quantity.clear();
            }
        });

        this.getChildren().addAll(name, shelf, quantity, add);
    }

    /**
     * Adds an observer to the observable
     *
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the observable
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies the observers
     *
     * @param buttonEnum the buttonEnum to be sent to the observers
     */
    public void notifyObservers(ButtonEnum buttonEnum, String name, int quantity, String shelf, boolean checked) {
        Grocery grocery = new Grocery(name, quantity, shelf, checked);
        for (Observer observer : observers) {
            observer.update(buttonEnum, grocery);
        }
    }
}
