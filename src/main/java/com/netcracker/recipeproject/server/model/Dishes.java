package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;

import java.util.ArrayList;

public interface Dishes {
    void addDish(Dish dish);
    void removeDish(Dish dish);
    void editDish(Dish dish);
    ArrayList<Dish> getDishes();
    int lastId();

}
