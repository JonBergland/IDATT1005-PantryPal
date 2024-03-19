package stud.ntnu.idatt1005.pantrypal;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.controllers.AddRecipeController;
import stud.ntnu.idatt1005.pantrypal.controllers.CookBookController;
import stud.ntnu.idatt1005.pantrypal.controllers.HomeController;
import stud.ntnu.idatt1005.pantrypal.controllers.LogInController;
import stud.ntnu.idatt1005.pantrypal.controllers.PantryController;
import stud.ntnu.idatt1005.pantrypal.controllers.ShoppingListController;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.models.Grocery;
import stud.ntnu.idatt1005.pantrypal.models.Shelf;
import stud.ntnu.idatt1005.pantrypal.registers.ShelfRegister;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;

import java.util.Date;

/**
 * The main class for the PantryPal application.
 * This class is responsible for starting the application
 * and initializing the view manager and controllers.
 */
public class PantryPal extends Application {

  private ShelfRegister shelfRegister;
  // View manager for the application.
  private ViewManager viewManager;
  // Controller for homeView.
  private HomeController homeController;
  // Controllers for the cookBook.
  private CookBookController cookBookController;
  // Controllers for adding a recipe.
  private AddRecipeController addRecipeController;
  // Controllers for the pantry.
  private PantryController pantryController;
  // Controllers for the shopping list.
  private ShoppingListController shoppingListController;
  // Controllers for the log in functionality.
  private LogInController logInController;

  /**
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   * The method creates the view manager and initializes the controllers.
   *
   * @param primaryStage The primary stage for this application,
   *                    onto which the application scene can be set.
   */
  @Override
  public void start(Stage primaryStage) {
    this.viewManager = new ViewManager(primaryStage);

    this.shelfRegister = new ShelfRegister();
    Shelf fridge = new Shelf("Fridge");
    System.out.println(fridge.getKey());
    fridge.addGrocery(new Grocery("Milk", 1, "L", new Date(2024, 12, 24)));
    shelfRegister.addShelf(fridge);

    // Init controllers
    homeController = new HomeController(viewManager);
    cookBookController = new CookBookController(viewManager);
    addRecipeController = new AddRecipeController(viewManager);
    pantryController = new PantryController(viewManager, shelfRegister);
    shoppingListController = new ShoppingListController(viewManager);
    logInController = new LogInController(viewManager);

    //Init view
    this.viewManager.setView(Route.HOME);

    Image icon = new Image("/images/PantryPalLogo.png");
    primaryStage.getIcons().add(icon);
    primaryStage.show();

  }

  /**
   * The main method is the entry point for the PantryPal application.
   * The method launches the application.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
