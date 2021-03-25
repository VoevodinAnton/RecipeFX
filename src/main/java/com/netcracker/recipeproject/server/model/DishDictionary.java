package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;

import java.util.ArrayList;

public class DishDictionary implements Dishes {
    public ArrayList<Dish> dishes;

    public DishDictionary(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {  //сделать проверку на дубликат
        for (Dish thisDish: dishes){
            if (thisDish.getName().equals(dish.getName()) && thisDish.getListOfIngr().equals(dish.getListOfIngr()) && thisDish.getCookingTime().equals(dish.getCookingTime())){
                //TODO: переопределить equals у ComponentDish или подумать над другим способом сравнения
                System.out.println("Обнаружен дубликат");
            }
        }
        dishes.add(dish);
    }

    @Override
    public void setDish(Dish dish) {
        int i = 0;
        for (Dish thisDish: dishes){
            if (thisDish.equals(dish)){
                dishes.set(i, dish);
                i++;
            }
        }
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }


}
