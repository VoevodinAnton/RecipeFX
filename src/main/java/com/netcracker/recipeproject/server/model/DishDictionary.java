package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.Dishes;

import java.util.ArrayList;

public class DishDictionary implements Dishes {
    public ArrayList<Dish> dishes;

    public DishDictionary() {
        dishes = new ArrayList<>();
    }


    public void addDish(Dish dish) {  //сделать проверку на дубликат
        if (!dishes.isEmpty()) {
            for (Dish thisDish : dishes) {
                if (thisDish.getName().equals(dish.getName()) && thisDish.getCookingTime().equals(dish.getCookingTime())) {
                    //TODO: переопределить equals у ComponentDish или подумать над другим способом сравнения
                    throw new DuplicateFoundException();
                }
            }

        }
        //TODO:add id of dish
        dishes.add(dish);
    }

    @Override
    public void setDish(Dish dish) {
        int i = 0;
        for (Dish thisDish : dishes) {
            if (thisDish.equals(dish)) {
                dishes.set(i, dish);
                i++;
            }
        }
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }


}
