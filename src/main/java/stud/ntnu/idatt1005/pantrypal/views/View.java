package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import stud.ntnu.idatt1005.pantrypal.controllers.Controller;
import stud.ntnu.idatt1005.pantrypal.controllers.Observer;
import stud.ntnu.idatt1005.pantrypal.enums.ButtonEnum;
import stud.ntnu.idatt1005.pantrypal.enums.Route;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;
import stud.ntnu.idatt1005.pantrypal.utils.FontPalette;
import stud.ntnu.idatt1005.pantrypal.views.components.NavBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * This class represents a View in the application. It extends the Scene class from JavaFX and
 * provides a base structure for other views. It has two types of views: HOME and DEFAULT. The HOME
 * view includes a title and a navigation bar. The DEFAULT view only includes a navigation bar.
 */
public class View extends Scene implements Observable {
  private final Route route;
  private final Controller controller;
  private final BorderPane root;

  /**
   * The list of observers that are observing this view
   */
  protected final List<Observer> observers = new ArrayList<>();

  /**
   * Constructor for the View class. Initializes the view based on the viewType.
   * The view is created with a BorderPane as the root node.
   * The view is styled with a style sheet based on the route.
   * The view is associated with a controller to handle the logic and actions.
   * The view is created with a size to match the screen size.
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
    this.widthProperty().lessThanOrEqualTo(getPrimary().getVisualBounds().getWidth() - 100);
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

  /**
   * Sets the view for the scene. The view is set based on the route of the scene.
   * The view includes a navigation bar and a title if the route is HOME.
   * The view only includes a navigation bar if the route is DEFAULT.
   *
   * @param borderPane The BorderPane to set the view in.
   */
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

  /**
   * Sets the root to a ScrollPane, and sets the current root to the content of the ScrollPane.
   */
  public void setScrollPane() {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(root);
    setRoot(scrollPane);
  }

  /**
   * Get the root node of the scene as a BorderPane.
   *
   * @return The root node of the scene.
   */
  public BorderPane getBorderPane() {
    return root;
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
   * Notifies the observers with the given enum and grocery.
   *
   * @param buttonEnum the enum to be notified.
   */
  protected void notifyObservers(ButtonEnum buttonEnum) {
    for (Observer observer : observers) {
      observer.update(buttonEnum);
    }
  }
}