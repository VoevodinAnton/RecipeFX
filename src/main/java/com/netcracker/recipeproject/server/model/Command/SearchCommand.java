package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.DishDictionary;

import java.util.ArrayList;

public class SearchCommand implements Command{
    DishDictionary dishDictionary;

    public SearchCommand(DishDictionary dishDictionary) {
        this.dishDictionary = dishDictionary;
    }

    @Override
    public Message Execute(Message message) {
        Object object = message.getObj();
        String searchString = (String) object;

        System.out.println(searchString);
        ArrayList<Dish> dishesToClient = new ArrayList<>(); //список блюд, который отправляется клиенту

        ArrayList<Dish> dishes = dishDictionary.getDishes();
        for (Dish dish : dishes) {
            if (dish.contains(searchString)) {
                dishesToClient.add(dish);
            }
        }
        System.out.println(
                "Отправлен список всех блюд");
        return new Message(0, dishesToClient);
    }
}
