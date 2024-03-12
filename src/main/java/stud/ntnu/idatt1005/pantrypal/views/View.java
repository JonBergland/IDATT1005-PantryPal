package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.views.components.NavBar;


/**
 * This class represents a View in the application.
 * It extends the Scene class from JavaFX and provides a base structure for other views.
 * It has two types of views: HOME and DEFAULT.
 * The HOME view includes a title and a navigation bar.
 * The DEFAULT view only includes a navigation bar.
 */
public class View extends Scene {
  /**
   * Enum representing the type of the view.
   */
  public enum ViewType {
    HOME,
    DEFAULT
  }

  /**
   * Root pane of the view.
   */
  BorderPane root;

  /**
   * Constructor for the View class.
   * Initializes the view based on the viewType.
   *
   * @param viewType Type of the view to be created.
   */
  public View(ViewType viewType) {
    super(new BorderPane(), getPrimary().getVisualBounds().getWidth(),
            getPrimary().getVisualBounds().getHeight());
    root = (BorderPane) getRoot();
    if (viewType == ViewType.HOME) {
      initializeHomeView();
    } else if (viewType == ViewType.DEFAULT) {
      initializeDefault();
    }
  }

  /**
   * Initializes the HOME view.
   * Sets up the title and navigation bar.
   */
  public void initializeHomeView() {
    VBox topContainer = new VBox(0);
    topContainer.setAlignment(javafx.geometry.Pos.CENTER);
    Text title = new Text("Pantry Pal");
    title.setFont(FontPalette.HEADER);
    topContainer.getChildren().addAll(
            title,
            new NavBar().getNavBar()
    );
    root.setTop(topContainer);
  }

  /**
   * Initializes the DEFAULT view.
   * Sets up the navigation bar.
   */
  public void initializeDefault() {
    root.setTop(new NavBar().getNavBar());
  }

}