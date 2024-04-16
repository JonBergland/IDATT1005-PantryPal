package stud.ntnu.idatt1005.pantrypal.models;

import java.util.UUID;

/**
 * This is an abstract class representing a model. It contains the key of the model
 */
public abstract class Model {
  private final String key;

  /**
   * Default constructor for Model class.
   * Generates a random UUID as the key for the model.
   */
  protected Model() {
    this.key = UUID.randomUUID().toString();
  }

  /**
   * Constructor for Model class
   * @param key the key of the model
   */
  protected Model(String key) {
    this.key = key;
  }

  /**
   * Gets the string key
   * @return the key
   */
  public String getKey() {
    return key;
  }
}
