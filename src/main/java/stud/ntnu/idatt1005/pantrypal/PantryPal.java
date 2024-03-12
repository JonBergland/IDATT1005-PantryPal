package stud.ntnu.idatt1005.pantrypal;

import javafx.application.Application;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.controllers.LogInController;
import stud.ntnu.idatt1005.pantrypal.controllers.LogOutController;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.controllers.RecipeController;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;

public class PantryPal extends Application {

  private ViewManager viewManager;
  private HomeController homeController;
  private CookBookController cookBookController;
  private RecipeController recipeController;
  private AddRecipeController addRecipeController;
  private PantryController pantryController;
  private ShoppingListController shoppingListController;
  private LogInController logInController;
  private LogOutController logOutController;

  @Override
  public void start(Stage primaryStage) {
    this.viewManager = new ViewManager(primaryStage);

    // Init controllers
    homeController = new HomeController(viewManager);
    cookBookController = new CookBookController(viewManager);
    recipeController = new RecipeController(viewManager);
    addRecipeController = new AddRecipeController(viewManager);
    pantryController = new PantryController(viewManager);
    shoppingListController = new ShoppingListController(viewManager);
    logInController = new LogInController(viewManager);
    logOutController = new LogOutController(viewManager);

    //Init view
    this.viewManager.setView(Route.HOME);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
