package stud.ntnu.idatt1005.pantrypal.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.Collection;

public class NodeUtils {
    public static void add(Node node, String styleClass) {
        node.getStyleClass().add(styleClass);
    }

    public static void add(Node node, Collection<String> styleClasses) {
        node.getStyleClass().addAll(styleClasses);
    }

    public static void addChildren(Pane pane, Node child) {
        pane.getChildren().add(child);
    }

    public static void addChildren(Pane pane, Node... children) {
        pane.getChildren().addAll(children);
    }
}
