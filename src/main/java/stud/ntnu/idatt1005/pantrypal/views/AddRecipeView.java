package stud.ntnu.idatt1005.pantrypal.views;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.views.components.AddGroceryListElement;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledButton;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledTextArea;
import stud.ntnu.idatt1005.pantrypal.views.components.StyledTextField;

/**
 * A class that extends View and creates a view for adding a recipe.
 * The goal is to provide a dedicated view for users to add a new recipe.
 * This class is associated with an AddRecipeController to handle the logic
 * and actions related to adding a recipe.
 */
public class AddRecipeView extends View {

  /**
   * Constructor for AddRecipeView.
   *
   * @param controller The AddRecipeController associated with this view.
   */
  public AddRecipeView(AddRecipeController controller) {
    super(controller, Route.ADD_RECIPE, "/styles/add-recipe.css");
    this.view();
  }

  private void view(){
    VBox form = new VBox();
    form.getStyleClass().add("form");
    form.setAlignment(Pos.CENTER);

    StyledTextField name = new StyledTextField("Name");
    StyledTextArea description = new StyledTextArea("Description");
    AddGroceryListElement ingredient = new AddGroceryListElement("");

    StyledButton submit = new StyledButton("Add Recipe");
    submit.setMaxWidth(Double.MAX_VALUE);

    form.getChildren().addAll(name, description, ingredient, submit);

    this.getBorderPane().setCenter(form);
  }
}
