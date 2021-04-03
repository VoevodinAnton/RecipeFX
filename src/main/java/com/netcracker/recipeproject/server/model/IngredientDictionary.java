package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;

import java.util.*;

public class IngredientDictionary implements Ingredients {
    private ArrayList<Ingredient> ingredients;

    public IngredientDictionary() {
        ingredients = new ArrayList<>();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        if (!ingredients.isEmpty()) {
            for (Ingredient thisIngredient : ingredients) {
                if (thisIngredient.getName().equalsIgnoreCase(ingredient.getName())) {
                    throw new DuplicateFoundException();
                } //TODO: add class of exceptions
            }
        }
        ingredient.setId(this.lastId() + 1);
        ingredients.add(ingredient);
    }

    @Override
    public void removeIngredient(Ingredient ingredient) {

    }

    @Override
    public void editIngredient(Ingredient ingredient) {

    }


    @Override
    public int lastId() {
        if (ingredients.isEmpty()) {
            return 0;
        }
        Comparator<Ingredient> ingredientComparator = Comparator.comparingInt(Ingredient::getId);
        ingredients.sort(ingredientComparator);
        return ingredients.get(ingredients.size() - 1).getId();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
