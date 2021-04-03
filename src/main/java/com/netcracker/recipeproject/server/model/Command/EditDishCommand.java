package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.DishDictionary;

public class EditDishCommand implements Command{
    DishDictionary dishDictionary;

    public EditDishCommand(DishDictionary dishDictionary) {
        this.dishDictionary = dishDictionary;
    }

    @Override
    public Message execute(Message message) {
        Object object = message.getObj();
        Dish dishEdit = (Dish) object;
        dishDictionary.editDish(dishEdit);
        return new Message(5, null);
    }
}
