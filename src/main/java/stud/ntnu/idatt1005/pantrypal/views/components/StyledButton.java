package stud.ntnu.idatt1005.pantrypal.views.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import stud.ntnu.idatt1005.pantrypal.utils.ColorPalette;

public class StyledButton extends Button {
  public enum Variant {
    SOLID, OUTLINE, DANGER, BLACK
  }
  public enum Size {
    MEDIUM, LARGE
  }

  public StyledButton(String text) {
    super(text);
    this.medium();
    this.solid();
  }

  public StyledButton(String text, Variant variant) {
    super(text);
    this.medium();

    if (variant == Variant.SOLID) {
      this.solid();
    }
    if(variant == Variant.OUTLINE) {
      this.outline();
    }
    if(variant == Variant.DANGER){
      this.danger();
    }
    if(variant == Variant.BLACK){
      this.black();
    }
  }

  public StyledButton(String text, Size size) {
    super(text);
    this.solid();

    if(size == Size.MEDIUM){
      this.medium();
    }
    if(size == Size.LARGE){
      this.large();
    }
  }

  public StyledButton(String text, Variant variant, Size size) {
    super(text);

    if(size == Size.MEDIUM){
      this.medium();
    }
    if(size == Size.LARGE){
      this.large();
    }

    if (variant == Variant.SOLID) {
      this.solid();
    }
    if(variant == Variant.OUTLINE) {
      this.outline();
    }
    if(variant == Variant.DANGER){
      this.danger();
    }
    if(variant == Variant.BLACK){
      this.black();
    }
  }

  private void medium(){
    this.setPadding(new Insets(10));
    this.setFont(Font.font("roboto", FontWeight.MEDIUM, 16));
  }

  private void large(){
    this.setPadding(new Insets(15));
    this.setFont(Font.font("roboto", FontWeight.MEDIUM, 20));
  }

  private void solid() {
    this.setBackground(
        new Background(new BackgroundFill(ColorPalette.PRIMARY, new CornerRadii(5), null)));
    this.setTextFill(ColorPalette.WHITE);

    this.setOnMouseEntered(e -> this.setBackground(
        new Background(new BackgroundFill(ColorPalette.PRIMARY_DARK, new CornerRadii(5), null))));
    this.setOnMouseExited(e -> this.setBackground(
        new Background(new BackgroundFill(ColorPalette.PRIMARY, new CornerRadii(5), null))));
  }

  private void outline() {
    this.setBackground(new Background(new BackgroundFill(ColorPalette.PRIMARY_LIGHT, new CornerRadii(5), null)));
    this.setBorder(new Border(new BorderStroke(ColorPalette.PRIMARY_DARK, BorderStrokeStyle.DASHED, new CornerRadii(5),
        new BorderWidths(2))));
    this.setTextFill(ColorPalette.PRIMARY_DARK);
  }

  private void danger(){
    this.setBackground(new Background(new BackgroundFill(ColorPalette.DANGER, new CornerRadii(5), null)));
    this.setTextFill(ColorPalette.WHITE);
  }

  private void black() {
    this.setBackground(new Background(new BackgroundFill(ColorPalette.BLACK, new CornerRadii(5), null)));
    this.setTextFill(ColorPalette.WHITE);
  }
}
