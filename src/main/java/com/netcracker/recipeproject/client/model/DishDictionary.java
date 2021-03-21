package com.netcracker.recipeproject.client.model;

import com.netcracker.recipeproject.library.Dish;

import java.util.ArrayList;

public class DishDictionary {

        public static ArrayList<Dish> dishes;

        public void addDish(Dish dish){
            dishes.add(dish);
        }

        public ArrayList<Dish> getDishes() {
            return dishes;
        }

    }

