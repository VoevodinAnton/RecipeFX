package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class IngredientDictionary implements Ingredients {
    ArrayList<Ingredient> ingredients;

    public IngredientDictionary() {
        ingredients = new ArrayList<>();
    }

    @Override
    public void add(Ingredient ingredient) {
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
    public void set(Ingredient ingredient) {

    }

    public int lastId() {
        if (ingredients.isEmpty()) {
            return 0;
        }
        Collections.sort(ingredients);
        return ingredients.get(ingredients.size() - 1).getId();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
