package stud.ntnu.idatt1005.pantrypal.utils;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.Collection;

/**
 * Utility class for handling JavaFX nodes and panes.
 */
public class NodeUtils {

  /**
   * Adds a style class to a node.
   *
   * @param node       the node to which the style class will be added
   * @param styleClass the style class to be added
   */
  public static void addClasses(Node node, String styleClass) {
    node.getStyleClass().add(styleClass);
  }

  /**
   * Adds multiple style classes to a node.
   *
   * @param node         the node to which the style classes will be added
   * @param styleClasses the collection of style classes to be added
   */
  public static void addClasses(Node node, Collection<String> styleClasses) {
    node.getStyleClass().addAll(styleClasses);
  }

  /**
   * Adds a child node to a pane.
   *
   * @param pane  the pane to which the child node will be added
   * @param child the child node to be added
   */
  public static void addChildren(Pane pane, Node child) {
    pane.getChildren().add(child);
  }

  /**
   * Adds multiple child nodes to a pane.
   *
   * @param pane     the pane to which the child nodes will be added
   * @param children the child nodes to be added
   */
  public static void addChildren(Pane pane, Node... children) {
    pane.getChildren().addAll(children);
  }
}
