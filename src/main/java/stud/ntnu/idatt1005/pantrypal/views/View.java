package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.Controller;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.views.components.NavBar;

import java.util.Objects;


/**
 * This class represents a View in the application. It extends the Scene class from JavaFX and
 * provides a base structure for other views. It has two types of views: HOME and DEFAULT. The HOME
 * view includes a title and a navigation bar. The DEFAULT view only includes a navigation bar.
 */
public class View extends Scene {

  private final Route route;

  private final Controller controller;
  /**
   * Root pane of the view.
   */
  private final BorderPane root;

  /**
   * Constructor for the View class. Initializes the view based on the viewType.
   *
   * @param route The route of the view.
   */
  public View(Controller controller, Route route, String stylePath) {
    super(new BorderPane(), getPrimary().getVisualBounds().getWidth(),
            getPrimary().getVisualBounds().getHeight());
    this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/components.css")).toExternalForm());
    this.getStylesheets().add(Objects.requireNonNull(getClass().getResource(stylePath)).toExternalForm());
    this.route = route;
    this.controller = controller;
    this.widthProperty().lessThanOrEqualTo(getPrimary().getVisualBounds().getWidth()-100);
    root = (BorderPane) getRoot();
    BorderPane borderPane = new BorderPane();
    borderPane.setPadding(new Insets(0));
    borderPane.maxWidthProperty().bind(root.widthProperty());
    BackgroundFill backgroundColor = new BackgroundFill(ColorPalette.PRIMARY_LIGHT, CornerRadii.EMPTY,
        Insets.EMPTY);
    root.setBackground(new Background(backgroundColor));
    this.setFill(ColorPalette.PRIMARY_LIGHT);
    this.view(root);
  }

  public void view(BorderPane borderPane) {
    if (route == Route.HOME) {
      VBox topContainer = new VBox(0);
      topContainer.setAlignment(javafx.geometry.Pos.CENTER);
      Text title = new Text("Pantry Pal");
      title.setFont(FontPalette.HEADER);
      topContainer.getChildren().addAll(
              title,
              new NavBar(controller).getNavBar()
      );
      borderPane.setTop(topContainer);
    } else {
      borderPane.setTop(new NavBar(controller).getNavBar());
    }
  }

  public void setScrollPane() {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(root);
    setRoot(scrollPane);
  }

  public BorderPane getBorderPane() {
    return root;
  }
}