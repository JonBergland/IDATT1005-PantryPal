package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Model;

import java.util.*;

public abstract class Register {
  Map<String, Model> registerMap;

  protected Register() {
    this.registerMap = new HashMap<>();
  }

  protected Register(Register registerMap) {
    this.registerMap = new HashMap<>(registerMap.getRegisterMap());
  }

  /**
   * Get the register map
   * @return the register map
   */
  public Map<String, Model> getRegisterMap() {
    return registerMap;
  }

  /**
   * Get a model from the register
   *
   * @param key the key of the model to be retrieved
   * @return the model with the specified name
   */
  public Model getItem(String key) {
    return registerMap.get(key);
  }

  /**
   * Add an item to the register
   *
   * @param  model item to be added
   * @throws IllegalArgumentException if the item already exists in the register
   */
  public abstract void addItem(Model model);

  /**
   * Remove an item from the register
   *
   * @param model the key of the item to be removed
   * @throws IllegalArgumentException if the item does not exist in the register
   */
  public void removeItem(Model model) {
    if (!registerMap.containsKey(model.getKey())) {
      throw new IllegalArgumentException("Item does not exist in register");
    }
    registerMap.remove(model.getKey());
  }

  /**
   * Sorts the items in the register in ascending order based on their names.
   *
   * @return a list of items sorted in ascending order
   */
  public List<Model> sortItemsAscending() {
    List<Model> sortedItems = new ArrayList<>(registerMap.values());
    sortedItems.sort(Comparator.comparing(Model::getKey));
    return sortedItems;
  }

  /**
   * Sorts the items in the register in descending order based on their names.
   *
   * @return a list of items sorted in descending order
   */
  public List<Model> sortItemsDescending() {
    List<Model> sortedGroceries = new ArrayList<>(registerMap.values());
    sortedGroceries.sort(Comparator.comparing(Model::getKey).reversed());
    return sortedGroceries;
  }
}
