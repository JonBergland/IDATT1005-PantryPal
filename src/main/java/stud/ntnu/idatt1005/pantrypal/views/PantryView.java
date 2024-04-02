package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;

import static stud.ntnu.idatt1005.pantrypal.utils.NodeUtils.addClasses;
import static stud.ntnu.idatt1005.pantrypal.utils.NodeUtils.addChildren;


/**
 * A class that extends View and creates a view for the pantry.
 * The goal is to provide a dedicated view for users to view their pantry.
 * This class is associated with an PantryController to handle the logic
 * and actions related to the pantry.
 */
public class PantryView extends View {

    /**
     * The controller responsible for managing the logic and
     * actions associated with the pantry functionality.
     */
    private final PantryController controller;

    /**
     * Constructor for PantryView.
     *
     * @param controller The controller for the view.
     */
    public PantryView(PantryController controller) {
        super(controller, Route.PANTRY, "/styles/pantry.css");
        this.controller = controller;
        this.setScrollPane();
    }

    public void render() {
        GridPane shelfGrid = new GridPane();

        Shelf[] shelves = controller.getShelves();

        int row = 0;
        int col = 0;

        for (Shelf shelf : shelves) {
            VBox shelfBox = this.shelf(shelf);
            shelfGrid.add(shelfBox, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        var addShelfButton = this.addShelfButton();
        addShelfButton.setOnAction(e -> {
            controller.addShelf();
        });
        shelfGrid.add(addShelfButton, col, row);
        shelfGrid.setAlignment(Pos.TOP_CENTER);
        this.getBorderPane().setCenter(shelfGrid);
    }

    private Button addShelfButton() {
        Button addShelfButton = new Button("Add new shelf");
        NodeUtils.addClasses(addShelfButton, "add-shelf-button");

        return addShelfButton;
    }

    private VBox shelf(Shelf shelf) {
        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        NodeUtils.addClasses(container, "shelf");

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);

        TextField title = new TextField(shelf.getName());
        NodeUtils.addClasses(title, "shelf-title-textfield");
        title.setEditable(false);
        StyledButton edit = new StyledButton("Edit");
        edit.setStyle("-fx-min-width: 80px;");
        edit.setOnAction(e -> {
            if (edit.getText().equals("Edit")) {
                title.setEditable(true);
                edit.setText("Save");
            } else {
                title.setEditable(false);
                edit.setText("Edit");
                this.controller.editShelfName(shelf, title.getText());
            }
        });
        StyledButton delete = new StyledButton("X", StyledButton.Variant.DELETE, StyledButton.Size.LARGE);
        delete.setStyle("-fx-min-width: 80px;");
        delete.setOnAction(e -> {
            this.controller.deleteShelf(shelf);
        });

        addChildren(header, title, edit, delete);

        Separator separator = new Separator();
        addClasses(separator, "shelf-separator");

        VBox groceryList = this.GroceryList(shelf, controller.getGroceries(shelf));

        addChildren(container, header, separator, groceryList);

        return container;
    }

    private VBox GroceryList(Shelf shelf, Grocery[] groceries){
        VBox container = new VBox();

        ScrollPane scrollContainer = new ScrollPane();
        NodeUtils.addClasses(scrollContainer, "grocery-list-scroll-container");
        VBox groceryList = new VBox();
        NodeUtils.addClasses(groceryList, "grocery-list");
        scrollContainer.setContent(groceryList);
        scrollContainer.setFitToWidth(true);

        for(Grocery grocery : groceries){
            HBox groceryItem = this.groceryItem(shelf, grocery);
            addChildren(groceryList, groceryItem);
        }

        HBox addGroceryButton = this.addGroceryButton(shelf);

        addChildren(container, scrollContainer, addGroceryButton);

        return container;
    }

    private HBox groceryItem(Shelf shelf, Grocery grocery){
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        HBox.setHgrow(container, Priority.ALWAYS);
        NodeUtils.addClasses(container, "grocery-item");

        HBox nameContainer = new HBox();
        nameContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(nameContainer, Priority.ALWAYS);
        NodeUtils.addClasses(nameContainer, "grocery-item-text-container");
        Text name = new Text(grocery.getName());
        NodeUtils.addClasses(name, "grocery-item-text");
        addChildren(nameContainer, name);

        HBox quantityContainer = new HBox();
        quantityContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(quantityContainer, Priority.ALWAYS);
        NodeUtils.addClasses(quantityContainer, "grocery-item-text-container");
        Spinner<Integer> spinner = createSpinner(grocery);
        spinner.setMaxWidth(100);
        spinner.setMaxHeight(50);
        NodeUtils.addClasses(spinner, "grocery-item-text");
        addChildren(quantityContainer, spinner);

        StyledButton deleteButton = new StyledButton("X", StyledButton.Variant.DELETE, StyledButton.Size.MEDIUM);
        deleteButton.setAlignment(Pos.CENTER);
        deleteButton.setOnAction(e -> {
            this.controller.deleteGrocery(shelf, grocery);
        });

        addChildren(container, nameContainer, quantityContainer, deleteButton);

        return container;
    }

    private HBox addGroceryButton(Shelf shelf){
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        NodeUtils.addClasses(container, "add-grocery-container");

        TextField groceryName = new TextField();
        groceryName.setPromptText("Name");
        groceryName.setMinWidth(60);
        addClasses(groceryName, "add-grocery-textfield");
        TextField groceryQuantity = new TextField();
        groceryQuantity.setPromptText("Quantity");
        groceryQuantity.setMinWidth(60);
        addClasses(groceryQuantity, "add-grocery-textfield");

        StyledButton addGroceryButton = new StyledButton("Add");
        addGroceryButton.setStyle("-fx-min-width: 60; -fx-padding: 10; -fx-background-insets: 0; -fx-border-insets: 0");
        addGroceryButton.setOnAction(e -> {
            try {
                this.controller.addGrocery(shelf, groceryName.getText(), Integer.parseInt(groceryQuantity.getText()));
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });

        addChildren(container, groceryName, groceryQuantity, addGroceryButton);

        return container;
    }

    protected Spinner<Integer> createSpinner(Grocery grocery) {
        Spinner<Integer> spinner = new Spinner<>();

        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, grocery.getQuantity());

        valueFactory.setAmountToStepBy(1);

        valueFactory.valueProperty().addListener((observable, oldValue, newValue) ->
            grocery.setQuantity(newValue)
        );

        spinner.setValueFactory(valueFactory);
        return spinner;
    }
}