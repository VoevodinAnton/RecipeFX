package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.DishDictionary;

public class RemoveDishCommand implements Command{
    DishDictionary dishDictionary;

    public RemoveDishCommand(DishDictionary dishDictionary) {
        this.dishDictionary = dishDictionary;
    }

    @Override
    public Message execute(Message message) {
        Object object = message.getObj();
        Dish dishRemove = (Dish) object;
        dishDictionary.removeDish(dishRemove);
        return new Message(CommandEnum.OK, null);
    }
}
