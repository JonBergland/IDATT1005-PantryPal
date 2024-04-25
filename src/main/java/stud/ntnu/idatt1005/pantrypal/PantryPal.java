package stud.ntnu.idatt1005.pantrypal;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import stud.ntnu.idatt1005.pantrypal.utils.ViewManager;

/**
 * The main class for the PantryPal application.
 * This class is responsible for starting the application
 * and initializing the view manager and controllers.
 */
public class PantryPal extends Application {

  public static String userName = null;
  // View manager for the application.
  private ViewManager viewManager;

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
    viewManager.init();

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
