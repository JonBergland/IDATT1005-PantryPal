package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.utils.DateUtils;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.utils.StyleUtils;

import java.util.Date;
import java.util.Optional;

public class ShelfComp extends BorderPane {

  private final Text shelfTitle;
  private final TextField titleTextField;
  private final VBox groceryList;

  public ShelfComp() {
    StyledButton addItemButton = new StyledButton("Add Item");
    addItemButton.setOnAction(e -> addItem());

    this.shelfTitle = new Text();
    this.shelfTitle.setFont(FontPalette.HEADER);

    StyledButton setCustomTitleButton = new StyledButton("Set Custom Title", StyledButton.Variant.OUTLINE);
    setCustomTitleButton.setFont(FontPalette.SMALL);
    setCustomTitleButton.setOnAction(e -> setCustomTitle());

    this.titleTextField = new TextField();
    this.titleTextField.setEditable(false);
    this.titleTextField.setFont(FontPalette.HEADER);
    this.titleTextField.setBackground(StyleUtils.TRANSPARENT_BACKGROUND);

    StackPane titlePane = new StackPane(this.shelfTitle);
    titlePane.setAlignment(Pos.TOP_CENTER);

    HBox titleBox = new HBox(setCustomTitleButton, titlePane);
    titleBox.setAlignment(Pos.TOP_CENTER);
    titleBox.setSpacing(10);
    StackPane.setMargin(titlePane, new Insets(0, 0, 10, 0));

    Separator separator = new Separator();
    separator.setMaxWidth(Double.MAX_VALUE);

    StackPane buttonPane = new StackPane(addItemButton);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setPadding(new Insets(10));

    // VBox to hold grocery items
    groceryList = new VBox();
    groceryList.setAlignment(Pos.CENTER);
    groceryList.setSpacing(10);

    // ScrollPane to enable scrolling if there are many grocery items
    ScrollPane scrollPane = new ScrollPane(groceryList);
    scrollPane.setFitToWidth(true);

    // Set the title, separator, and empty field at the top
    setTop(titleBox);
    setCenter(separator);
    setAlignment(separator, Pos.TOP_CENTER);

    // Set the grocery list in the center with scroll functionality
    setCenter(scrollPane);
    setAlignment(scrollPane, Pos.CENTER);

    // Set the button in the center
    setBottom(buttonPane);
    setAlignment(buttonPane, Pos.CENTER);

    // Add some styling
    setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");
  }

  private void setCustomTitle() {
    TextInputDialog dialog = new TextInputDialog(shelfTitle.getText());
    dialog.setTitle("Set Custom Title");
    dialog.setHeaderText("Enter a custom title for the shelf:");
    dialog.setContentText("Title:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(this::updateShelfTitle);

    // It's good practice to close the dialog explicitly if needed.
    dialog.close();
  }


  private void updateShelfTitle(String newTitle) {
    shelfTitle.setText(newTitle);
    titleTextField.setText(newTitle);
  }

  private void addItem() {
    // Create the custom dialog to input grocery details
    Dialog<Grocery> dialog = createGroceryDialog();

    // Show the dialog and process the result
    Optional<Grocery> result = dialog.showAndWait();
    result.ifPresent(this::displayGrocery);
  }

  private Dialog<Grocery> createGroceryDialog() {
    Dialog<Grocery> dialog = new Dialog<>();

    // Create the input fields and labels
    TextField nameField = new TextField();
    TextField quantityField = new TextField();
    TextField categoryField = new TextField();
    DatePicker expirationDatePicker = new DatePicker();

    // Create the button types (OK and Cancel)
    ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

    // Create the grid and set its padding
    GridPane grid = createGroceryDialogGrid(nameField, quantityField, categoryField, expirationDatePicker);

    // Enable/Disable addButton based on whether a name is entered
    Node addButtonNode = dialog.getDialogPane().lookupButton(addButton);
    addButtonNode.setDisable(true);

    // Listen for changes in nameField text property to enable/disable the addButton
    nameField.textProperty().addListener((observable, oldValue, newValue) ->
        addButtonNode.setDisable(newValue.trim().isEmpty()));

    // Set the grid as the dialog content
    dialog.getDialogPane().setContent(grid);

    // Request focus on the nameField by default
    Platform.runLater(nameField::requestFocus);

    // Convert the result to a grocery object when the addButton is clicked
    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == addButton) {
        try {
          String name = nameField.getText();
          int quantity = Integer.parseInt(quantityField.getText());
          String category = categoryField.getText();
          LocalDate expirationDate = expirationDatePicker.getValue();

          Date utilDate = DateUtils.convertLocalDateToDate(expirationDate);

          return new Grocery(name, quantity, category, utilDate);
        } catch (NumberFormatException e) {
          // Handle invalid input
          return null;
        }
      }
      return null;
    });

    return dialog;
  }

  private GridPane createGroceryDialogGrid(TextField nameField, TextField quantityField, TextField categoryField, DatePicker expirationDatePicker) {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    grid.add(new Label("Name:"), 0, 0);
    grid.add(nameField, 1, 0);
    grid.add(new Label("Quantity:"), 0, 1);
    grid.add(quantityField, 1, 1);
    grid.add(new Label("Category:"), 0, 2);
    grid.add(categoryField, 1, 2);
    grid.add(new Label("Expiration Date:"), 0, 3);
    grid.add(expirationDatePicker, 1, 3);

    return grid;
  }

  private void displayGrocery(Grocery grocery) {
    HBox groceryPane = createGroceryPane(grocery);
    groceryList.getChildren().add(groceryPane);
  }

  private HBox createGroceryPane(Grocery grocery) {
    HBox groceryPane = new HBox();
    groceryPane.setAlignment(Pos.CENTER);
    groceryPane.setSpacing(10);

    groceryPane.setMaxWidth(Double.MAX_VALUE);

    groceryPane.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 10;");

    Label groceryLabel = new Label(formatGrocery(grocery));
    groceryLabel.setBackground(StyleUtils.TRANSPARENT_BACKGROUND);
    groceryLabel.setPadding(new Insets(10));

    StyledButton deleteButton = new StyledButton("Delete", StyledButton.Variant.DANGER, StyledButton.Size.MEDIUM);
    deleteButton.setOnAction(e -> deleteGrocery(groceryPane, grocery));

    groceryPane.getChildren().addAll(groceryLabel, deleteButton);

    return groceryPane;
  }

  private void deleteGrocery(HBox groceryPane, Grocery grocery) {
    groceryList.getChildren().remove(groceryPane);
  }

  private String formatGrocery(Grocery grocery) {
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = dateFormat.format(grocery.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

    return String.format("%s: %stk - %s - %s",
        grocery.getName(), grocery.getQuantity(), grocery.getCategory(), formattedDate);
  }
}
