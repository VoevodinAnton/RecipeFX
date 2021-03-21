package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;

import java.util.ArrayList;

public class DishDictionary {
    public ArrayList<Dish> dishes;

    public void addDish(Dish dish){
        dishes.add(dish);
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }


}
