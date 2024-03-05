package stud.ntnu.idatt1005.pantrypal.views.components;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

public class StyledButton extends Button {
  public StyledButton(String text){
    super(text);
    this.setBackground(new Background(new BackgroundFill(ColorPalette.PRIMARY, null, null)));
    this.setTextFill(ColorPalette.WHITE);
    this.setPadding(new Insets(10));
  }
  public StyledButton(String text, String variant){
    super(text);

    if(variant.equals("default") || variant.isEmpty()){
      this.setBackground(new Background(new BackgroundFill(ColorPalette.PRIMARY, null, null)));
      this.setTextFill(ColorPalette.WHITE);
      this.setPadding(new Insets(10));
    }
  }
}
