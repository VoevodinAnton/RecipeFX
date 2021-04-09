package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.IngredientDictionary;

public class OutputOfAllIngredientsCommand implements Command{
    IngredientDictionary ingredientDictionary;

    public OutputOfAllIngredientsCommand(IngredientDictionary ingredientDictionary) {
        this.ingredientDictionary = ingredientDictionary;
    }

    @Override
    public Message execute(Message message) {
        return new Message(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, ingredientDictionary.getAllIngredients());
    }
}
