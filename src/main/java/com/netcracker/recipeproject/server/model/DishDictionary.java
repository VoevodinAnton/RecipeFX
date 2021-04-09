package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.Dishes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DishDictionary implements Dishes {
    public ArrayList<Dish> dishes;

    public DishDictionary() {
        dishes = new ArrayList<>();
    }


    public void addDish(Dish dish) {
        if (!(dishes.isEmpty())) {
            for (Dish thisDish : dishes) {
                if (thisDish.equals(dish)) {
                    throw new DuplicateFoundException();
                }
            }
        }
        dish.setId(this.lastId() + 1);
        dishes.add(dish);
    }

    @Override
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    @Override
    public void editDish(Dish dish) { //TODO: Разделить функцию поиска и функцию редакктирования
        int i = 0;
        for (Dish thisDish : dishes) {
            if (thisDish.getId() == dish.getId()) {
                dishes.set(i, dish);
                i++;
            }
        }
    }

    public ArrayList<Dish> getAllDishes() {
        return dishes;
    }

    @Override
    public int lastId() {
        if (dishes.isEmpty()) {
            return 0;
        }
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getId);
        dishes.sort(dishComparator);
        return dishes.get(dishes.size() - 1).getId();
    }


}
