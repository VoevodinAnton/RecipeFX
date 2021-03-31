package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.Command.Developer;
import com.netcracker.recipeproject.server.model.Command.OutputOfAllDishesCommand;
import com.netcracker.recipeproject.server.model.Command.SearchCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Store {
    DishDictionary dishDictionary;
    IngredientDictionary ingredientDictionary;
    Developer developer;

    public Store() {
        dishDictionary = new DishDictionary();
        ingredientDictionary = new IngredientDictionary();
        developer = new Developer(
                new SearchCommand(dishDictionary),
                new OutputOfAllDishesCommand(dishDictionary));


        /////////////////////////////////
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient sausage = new Ingredient("колбаса", "гр");
        ingredientDictionary.add(egg);
        ingredientDictionary.add(sausage);
        DishComponent sausage1 = new DishComponent(sausage, 1);
        DishComponent egg3 = new DishComponent(egg, 3);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);
        Dish omelette = new Dish(ingredientsOfOmelette, "omelette", "10 минут");
        dishDictionary.addDish(omelette);
        //////////////////////////////
    }


    public Message doCommand(Message message) { //перенести в другой класс, в модель

        int flag = message.getFlag();
        Object object = message.getObj();

        switch (flag) { //сделать ENUM
            case 0: //Search
                return developer.searchDish(message);
            case 1: //output of all dishes
                System.out.println("Размер списка блюд " + dishDictionary.getDishes().size());
                return developer.dishesOutput(message);
            case 2:
                Dish dishEdit = (Dish) object;
                dishDictionary.setDish(dishEdit);
                return null;

            case 3:
                Dish dishAdd = (Dish) object;
                dishDictionary.addDish(dishAdd);
                return null;
            case 6:
                Ingredient ingredientAdd = (Ingredient) object;
                try {
                    ingredientDictionary.add(ingredientAdd);
                } catch (DuplicateFoundException e) {
                    System.err.println("Обнаружен дупликат");
                    return new Message(3, ingredientAdd);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            case 8:
                System.out.println("Размер ингредиентов " + ingredientDictionary.getIngredients().size());
                return new Message(1, ingredientDictionary.getIngredients());
            default:
                return null;

        }

    }
}

