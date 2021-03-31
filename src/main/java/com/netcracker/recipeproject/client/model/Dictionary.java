package com.netcracker.recipeproject.client.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;

import java.util.ArrayList;

public class Dictionary {

        private static ArrayList<Dish> dishes;
        private static ArrayList<Ingredient> ingredients;

        public static ArrayList<Dish> getDishes() {
            return dishes;
        }

    public static void setDishes(ArrayList<Dish> dishes) {
        Dictionary.dishes = dishes;
    }


    public static ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public static void setIngredients(ArrayList<Ingredient> ingredients) {
        Dictionary.ingredients = ingredients;
    }
}

