package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.DishDictionary;
import com.netcracker.recipeproject.server.model.IngredientDictionary;

import java.util.ArrayList;

public class RemoveIngredientCommand implements Command{

    IngredientDictionary ingredientDictionary;

    public RemoveIngredientCommand(IngredientDictionary ingredientDictionary) {
        this.ingredientDictionary = ingredientDictionary;
    }

    @Override
    public Message execute(Message message) {
        Object object = message.getObj();
        Ingredient ingredient = (Ingredient) object;
        ingredientDictionary.removeIngredient(ingredient);
        return new Message(5, null);
    }
}
