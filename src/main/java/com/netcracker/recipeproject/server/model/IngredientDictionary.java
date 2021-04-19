package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;

import java.io.IOException;
import java.util.*;

public class IngredientDictionary {
    private static IngredientDictionary instance;
    private ArrayList<Ingredient> ingredients;

    public IngredientDictionary() {
        ingredients = new ArrayList<>();
    }

    public static synchronized IngredientDictionary getInstance() throws IOException {
        if (instance == null) {
            instance = new IngredientDictionary();
        }
        return instance;
    }

    public synchronized ArrayList<Ingredient> getAllIngredients() {
        return ingredients;
    }

}
