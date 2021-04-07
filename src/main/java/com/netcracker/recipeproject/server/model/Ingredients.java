package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Ingredient;

import java.util.ArrayList;
import java.util.TreeSet;

public interface Ingredients {
    void addIngredient(Ingredient ingredient);
    void removeIngredient(Ingredient ingredient);
    void editIngredient(Ingredient ingredient);
    ArrayList<Ingredient> getAllIngredients();
    int lastId();

}
