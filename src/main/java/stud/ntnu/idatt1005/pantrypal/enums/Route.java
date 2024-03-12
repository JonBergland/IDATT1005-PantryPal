package stud.ntnu.idatt1005.pantrypal.enums;

public enum Route {
  HOME, PANTRY, SHOPPING_LIST, RECIPE, COOKBOOK, ADD_RECIPE, LOGIN, LOGOUT;

  @Override
  public String toString() {
    // Capitalize the first letter of the enum name
    String s = this.name().toLowerCase();
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }
}
