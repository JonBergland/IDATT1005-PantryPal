package stud.ntnu.idatt1005.pantrypal.registers;

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
     * Add a recipe to the recipe register.
     *
     * @param recipe the recipe to be added to the recipe register.
     */
    public void addRecipe(Recipe recipe) throws IllegalArgumentException {
        super.addModel(recipe);
    }
}