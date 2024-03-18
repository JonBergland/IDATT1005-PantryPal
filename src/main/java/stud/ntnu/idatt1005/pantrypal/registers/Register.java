package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Model;

import java.util.*;

public abstract class Register<T extends Model> {
  LinkedHashMap<String, T> register;

  protected Register() {
    this.register = new LinkedHashMap<>();
  }

  protected Register(Register<T> register) {
    this.register = new LinkedHashMap<>(register.getRegister());
  }

  /**
   * Get the register map
   * @return the register map
   */
  public LinkedHashMap<String, T> getRegister() {
    return this.register;
  }

  /**
   * Get a model from the register
   *
   * @param key the key of the model to be retrieved
   * @throws IllegalArgumentException if the item does not exist in the register
   * @return the model with the specified name
   */
  protected T getModel(String key) throws IllegalArgumentException {
    if(!register.containsKey(key)){
      throw new IllegalArgumentException("Item does not exist in register");
    }
    return register.get(key);
  }

  /**
   * Add an item to the register
   *
   * @param  model item to be added
   */
  protected void addModel(T model){
    register.put(model.getKey(), model);
  };

  /**
   * Remove an item from the register
   *
   * @param model the key of the item to be removed
   * @throws IllegalArgumentException if the item does not exist in the register
   */
  protected void removeModel(T model) throws IllegalArgumentException {
    if (!register.containsKey(model.getKey())) {
      throw new IllegalArgumentException("Item does not exist in register");
    }
    register.remove(model.getKey());
  }

  /**
   * Sorts the items in the register in ascending order based on their names.
   *
   * @return a list of items sorted in ascending order
   */
  protected List<T> sortModelsAscending() {
    List<T> sortedItems = new ArrayList<>(register.values());
    sortedItems.sort(Comparator.comparing(T::getKey));
    return sortedItems;
  }

  /**
   * Sorts the items in the register in descending order based on their names.
   *
   * @return a list of items sorted in descending order
   */
  protected List<T> sortModelsDescending() {
    List<T> sortedGroceries = new ArrayList<>(register.values());
    sortedGroceries.sort(Comparator.comparing(T::getKey).reversed());
    return sortedGroceries;
  }
}
