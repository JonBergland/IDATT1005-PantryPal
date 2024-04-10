package stud.ntnu.idatt1005.pantrypal.views;

import static javafx.stage.Screen.getPrimary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
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
   * List of observers observing this view.
   */
  protected final List<Observer> observers = new ArrayList<>();

  /**
   * Constructs a View object with a specified controller, route, and style path.
   * The view is created with a BorderPane as the root node
   * and styled with a style sheet based on the route.
   * The view is associated with a controller to handle the logic and actions.
   * The view is created with a size to match the screen size.
   *
   * @param controller  The controller associated with the view.
   * @param route       The route of the view.
   * @param stylePath   The path to the style sheet for the view.
   */
  public View(Controller controller, Route route, String stylePath) {
    super(new BorderPane(), getPrimary().getVisualBounds().getWidth(),
            getPrimary().getVisualBounds().getHeight());
    this.getStylesheets().add(Objects.requireNonNull(getClass()
            .getResource("/styles/components.css")).toExternalForm());
    this.getStylesheets().add(Objects.requireNonNull(getClass()
            .getResource(stylePath)).toExternalForm());
    this.route = route;
    this.controller = controller;
    this.widthProperty().lessThanOrEqualTo(getPrimary().getVisualBounds().getWidth() - 100);
    root = (BorderPane) getRoot();
    BorderPane borderPane = new BorderPane();
    borderPane.setPadding(new Insets(0));
    borderPane.maxWidthProperty().bind(root.widthProperty());
    BackgroundFill backgroundColor = new BackgroundFill(ColorPalette.PRIMARY_LIGHT,
            CornerRadii.EMPTY, Insets.EMPTY);
    root.setBackground(new Background(backgroundColor));
    this.setFill(ColorPalette.PRIMARY_LIGHT);
    this.view(root);
  }

  /**
   * Sets up the view for the scene based on the route of the scene.
   * If the route is HOME, the view includes a navigation bar and a title.
   * For other routes, the view only includes a navigation bar.
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
              new NavBar(controller)
      );
      borderPane.setTop(topContainer);
    } else {
      borderPane.setTop(new NavBar(controller));
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
   * Adds an observer to the list of observers for this view.
   *
   * @param observer The observer to be added.
   * @throws IllegalArgumentException If the observer is null.
   */
  @Override
  public void addObserver(Observer observer) throws IllegalArgumentException {
    if (observer != null) {
      if (!observers.contains(observer)) {
        observers.add(observer);
      }
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  /**
   * Removes an observer from the list of observers for this view.
   *
   * @param observer The observer to be removed.
   * @throws IllegalArgumentException If the observer is null.
   */
  @Override
  public void removeObserver(Observer observer) throws IllegalArgumentException {
    if (observer != null) {
      if (observers.contains(observer)) {
        observers.remove(observer);
      }
    } else {
      throw new IllegalArgumentException("Observer cannot be null");
    }
  }

  /**
   * Notifies all observers with the given ButtonEnum.
   *
   * @param buttonEnum The ButtonEnum to notify the observers with.
   */
  protected void notifyObservers(ButtonEnum buttonEnum) {
    List<Observer> observersCopy = new ArrayList<>(this.observers);
    for (Observer observer : observersCopy) {
      observer.update(buttonEnum);
    }
  }
}