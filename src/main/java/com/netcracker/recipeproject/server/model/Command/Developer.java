package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.DishDictionary;
import com.netcracker.recipeproject.server.model.IngredientDictionary;

public class Developer {
   Command search;
   Command outputOfAllDishes;

    public Developer(Command search, Command outputOfAllDishes) {
        this.search = search;
        this.outputOfAllDishes = outputOfAllDishes;
    }

    public Message searchDish(Message message){
        return search.Execute(message);
    }

    public Message dishesOutput(Message message){
        return outputOfAllDishes.Execute(message);
    }
}
