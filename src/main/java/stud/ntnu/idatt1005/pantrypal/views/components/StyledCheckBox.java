package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

public class StyledCheckBox extends CheckBox {

  public StyledCheckBox(String text) {
    super(text);
    this.setPadding(new Insets(10));
  }

  public StyledCheckBox(String text, boolean selected) {
    super(text);
    this.setSelected(selected);
    this.setPadding(new Insets(10));
  }

  public StyledCheckBox(String text, boolean selected, String variant) {
    super(text);
    this.setSelected(selected);
    this.setPadding(new Insets(10));
    if(variant.equals("default") || variant.isEmpty()){
      this.setBackground(new Background(new BackgroundFill(ColorPalette.WHITE, null, null)));
    }
  }

}
