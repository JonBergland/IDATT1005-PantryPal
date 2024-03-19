package stud.ntnu.idatt1005.pantrypal.registers;

import stud.ntnu.idatt1005.pantrypal.models.Model;
import stud.ntnu.idatt1005.pantrypal.models.Recipe;


/**
 * This is a register class for recipes.
 * It contains a register of recipes structured in a hashmap.
 * Goal: act as a register for recipes.
 */
public class RecipeRegister extends Register<Recipe> {
    /**
     * Constructor for the RecipeRegister class.
     * Initializes the recipe register.
     */
    public RecipeRegister() {
        super();
    }

    /**
     * Deep-copy constructor for the RecipeRegister class.
     *
     * @param register the register to be used.
     */
    public RecipeRegister(RecipeRegister register) {
        super(register);
    }

    /**
     * Returns an error message when a recipe does not exist in the register.
     * @return a string error message.
     */
    protected String getErrorMessage() {
        return "Recipe does not exist in register";
    }

    /**
     * Retrieves a recipe from the register by its name.
     *
     * @param name the name of the recipe to be retrieved.
     * @return the recipe with the specified name.
     * @throws IllegalArgumentException if the recipe does not exist in the register.
     */
    public Recipe getRecipe(String name) throws IllegalArgumentException {
        return super.getModel(name);
    }

    /**
     * Add a recipe to the recipe register.
     *
     * @param recipe the recipe to be added to the recipe register.
     */
    public void addRecipe(Recipe recipe) {
        super.addModel(recipe);
    }

    /**
     * Removes a recipe from the register.
     *
     * @param recipe the recipe to be removed from the register.
     */
    public void removeRecipe(Recipe recipe) {
        super.removeModel(recipe);
    }
}