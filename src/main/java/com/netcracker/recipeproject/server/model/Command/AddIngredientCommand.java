package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.IngredientDictionary;

public class AddIngredientCommand implements Command{
    IngredientDictionary ingredientDictionary;

    public AddIngredientCommand(IngredientDictionary ingredientDictionary) {
        this.ingredientDictionary = ingredientDictionary;
    }

    @Override
    public Message execute(Message message) {
        Object object = message.getObj();
        Ingredient ingredientAdd = (Ingredient) object;
        try {
            ingredientDictionary.addIngredient(ingredientAdd);
        } catch (DuplicateFoundException e) {
            System.err.println("Обнаружен дупликат");
            return new Message(3, ingredientAdd);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return new Message(5, null);
    }
}
