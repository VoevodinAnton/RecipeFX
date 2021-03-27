package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Ingredient;

import java.util.ArrayList;
import java.util.TreeSet;

public interface Ingredients {
    void add(Ingredient ingredient);
    void set(Ingredient ingredient);
    ArrayList<Ingredient> getIngredients();

}
