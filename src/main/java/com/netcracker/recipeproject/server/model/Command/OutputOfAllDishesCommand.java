package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.DishDictionary;

public class OutputOfAllDishesCommand implements Command{
    DishDictionary dishDictionary;

    public OutputOfAllDishesCommand(DishDictionary dishDictionary) {
        this.dishDictionary = dishDictionary;
    }

    @Override
    public Message execute(Message message) {
        return new Message(0, dishDictionary.getAllDishes());
    }
}
