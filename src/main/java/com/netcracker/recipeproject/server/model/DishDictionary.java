package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;

import java.io.IOException;
import java.util.ArrayList;

public class DishDictionary {
    private static DishDictionary instance;
    private ArrayList<Dish> dishes;

    private DishDictionary() {
        dishes = new ArrayList<>();
    }

    public static synchronized DishDictionary getInstance() throws IOException {
        if (instance == null) {
            instance = new DishDictionary();
        }
        return instance;
    }

    public ArrayList<Dish> getAllDishes() {
        return dishes;
    }

}
