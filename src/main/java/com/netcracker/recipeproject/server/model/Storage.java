package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;

import java.io.IOException;

public interface Storage{

    void addDish(Dish dish) throws IOException;
    void addIngredient(Ingredient ingredient) throws IOException;
    void removeDish(Dish dish) throws IOException;
    void removeDish(Ingredient ingredient) throws IOException;
    void removeIngredient(Ingredient ingredient) throws IOException;
    void editDish(Dish dish) throws IOException;
    void editIngredient(Ingredient ingredient) throws IOException;
}
