package stud.ntnu.idatt1005.pantrypal.models;

/**
 * This is an abstract class representing a model. It contains the key of the model
 */
public abstract class Model {
  private final String key;

  /**
   * Constructor for Model class
   * @param key the key of the model
   */
  protected Model(String key) {
    this.key = key;
  }

  /**
   * Deep-copy constructor of Model-class
   * @param model the model to be copied
   */
  protected Model(Model model) {
    this.key = model.getKey();
  }

  /**
   * Gets the string key
   * @return the key
   */
  public String getKey() {
    return key;
  }
}
