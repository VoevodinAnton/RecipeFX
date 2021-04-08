package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.DishDictionary;

public class AddDishCommand implements Command{
    DishDictionary dishDictionary;

    public AddDishCommand(DishDictionary dishDictionary) {
        this.dishDictionary = dishDictionary;
    }

    @Override
    public Message execute(Message message) {
        Object object = message.getObj();
        Dish dishAdd = (Dish) object;
        try{
            dishDictionary.addDish(dishAdd);
        } catch (DuplicateFoundException e){
            System.out.println("Обнаружен  дупликат");
            return new Message(CommandEnum.ADDING_A_DUPLICATE_DISH, dishAdd);
        }
        return new Message(CommandEnum.OK, null);
    }
}
