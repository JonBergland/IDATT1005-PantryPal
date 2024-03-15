package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;
import java.util.Date;

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
        StyledTextField category = new StyledTextField("Category");
        StyledTextField quantity = new StyledTextField("Quantity");
        StyledButton add = new StyledButton("Add", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

        name.setMaxWidth(200);
        category.setMaxWidth(200);
        quantity.setMaxWidth(100);

        add.setOnAction(e -> {
            if (!name.getText().isEmpty() && !category.getText().isEmpty() && !quantity.getText().isEmpty()) {
                notifyObservers(ButtonEnum.ADD, name.getText(), Integer.parseInt(quantity.getText()), category.getText(), new Date());
                name.clear();
                category.clear();
                quantity.clear();
            }
        });

        this.getChildren().addAll(name, category, quantity, add);
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
    public void notifyObservers(ButtonEnum buttonEnum, String name, int quantity, String category, Date expirationDate) {
        Grocery grocery = new Grocery(name, quantity, category, expirationDate);
        for (Observer observer : observers) {
            observer.update(buttonEnum, grocery);
        }
    }
}
