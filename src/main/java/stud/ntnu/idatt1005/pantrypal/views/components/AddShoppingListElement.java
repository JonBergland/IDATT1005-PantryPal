package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.views.Observable;

import java.util.ArrayList;

public class AddShoppingListElement extends HBox implements Observable {
    ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Constructor for the add shopping list element
     */
    public AddShoppingListElement() {
        super();
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/pantry.css")).toExternalForm());
        this.setAlignment(Pos.CENTER);
        NodeUtils.addClasses(this, "add-grocery-container");

        StyledTextField name = new StyledTextField("Name");
        StyledTextField shelf = new StyledTextField("Shelf");
        StyledTextField quantity = new StyledTextField("Quantity");
        StyledButton add = getStyledButton(name, shelf, quantity);

        this.getChildren().addAll(
            styleText(name, 200),
            styleText(shelf, 200),
            styleText(quantity, 100),
            add);
    }

    /**
     * Creates a styled button with the given text fields and returns it.
     * The button is styled with the add-grocery-button class.
     *
     * @param name the name text field
     * @param shelf the shelf text field
     * @param quantity the quantity text field
     * @return a styled button
     */
    private StyledButton getStyledButton(StyledTextField name, StyledTextField shelf, StyledTextField quantity) {
        StyledButton add = new StyledButton("Add", StyledButton.Variant.SOLID, StyledButton.Size.MEDIUM);

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
        return add;
    }

    /**
     * Styles the text field given as parameter and returns
     * a StackPane with the text field.
     * The text field is styled with the add-grocery-textfield class
     * and has a max width of the given width.
     *
     * @param textField the text field to be styled
     * @param width the width of the text field
     * @return A Stackpane with the styled text field
     */
    private StackPane styleText(StyledTextField textField, int width) {
        addClasses(textField, "add-grocery-textfield");
        textField.setMaxWidth(width);

        StackPane stackPane = new StackPane(textField);
        stackPane.setAlignment(Pos.CENTER);

        return stackPane;
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
