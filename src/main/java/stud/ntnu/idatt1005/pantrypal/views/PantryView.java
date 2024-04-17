package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;

import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.utils.NodeUtils;
import stud.ntnu.idatt1005.pantrypal.views.components.AddGroceryListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.GroceryListElement;
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

  /**
   * This method renders the pantry view.
   * It creates a grid of shelves and groceries.
   * It also creates a button to add a new shelf.
   *
   * @param shelves the shelves to be shown
   */
  public void render(Shelf[] shelves) {
    GridPane shelfGrid = new GridPane();

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

  /**
   * Creates a button for adding a shelf.
   *
   * @return a button for adding a shelf
   */
  private Button addShelfButton() {
    Button addShelfButton = new Button("Add new shelf");
    NodeUtils.addClasses(addShelfButton, "add-shelf-button");

        return addShelfButton;
    }

  /**
   * Creates a VBox for representing a single shelf.
   * It contains a header with the shelf name and
   * buttons for editing and deleting the shelf.
   * It also contains a list of groceries in the shelf.
   *
   * @param shelf the shelf object to be shown
   * @return a VBox containing the visual representation of a shelf
   */
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

  /**
   * Creates a VBox for representing a list of groceries.
   * Used to display the groceries in a shelf.
   *
   * @param shelf     the shelf containing the groceries to be shown
   * @param groceries the groceries to be shown
   * @return a VBox containing the visual representation of a list of groceries
   */
  private VBox groceryList(Shelf shelf, Grocery[] groceries) {
    ScrollPane scrollContainer = new ScrollPane();
    NodeUtils.addClasses(scrollContainer, "grocery-list-scroll-container");
    VBox groceryList = new VBox();
    NodeUtils.addClasses(groceryList, "grocery-list");
    scrollContainer.setContent(groceryList);
    scrollContainer.setFitToWidth(true);

        for(Grocery grocery : groceries){
            GroceryListElement element = new GroceryListElement.GroceryListElementBuilder(grocery)
                .text(grocery.getName())
                .quantity()
                .build();
            for (Observer observer : observers) {
                element.addObserver(observer);
            }
            addChildren(groceryList, element.getPane());
        }

        AddGroceryListElement addGroceryButton = new AddGroceryListElement(shelf.getName());
        for (Observer observer : observers) {
            addGroceryButton.addObserver(observer);
        }

        addChildren(container, scrollContainer, addGroceryButton);

        return container;
    }
}