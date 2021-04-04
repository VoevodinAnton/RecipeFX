package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.Command.*;

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
                new OutputOfAllDishesCommand(dishDictionary),
                new EditDishCommand(dishDictionary),
                new AddDishCommand(dishDictionary),
                new RemoveDishCommand(dishDictionary),
                new RemoveIngredientCommand(ingredientDictionary),
                new AddIngredientCommand(ingredientDictionary));


        /////////////////////////////////
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient sausage = new Ingredient("колбаса", "гр");
        ingredientDictionary.addIngredient(egg);
        ingredientDictionary.addIngredient(sausage);
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

        switch (flag) { //сделать ENUM
            case 0: //Search
                return developer.searchDish(message);
            case 1: //output of all dishes
                System.out.println("Размер списка блюд " + dishDictionary.getDishes().size());
                return developer.dishesOutput(message);
            case 2: //edit a dish
                return developer.editDish(message);
            case 3: //add a dish
                return developer.addDish(message);
            case 4: //remove a dish
                return developer.removeDish(message);
            case 5: //remove a ingredient
                return developer.removeIngredient(message);
            case 6: //add ingredient
                return developer.addIngredient(message);
            case 8:
                System.out.println("Размер ингредиентов " + ingredientDictionary.getIngredients().size());
                return new Message(1, ingredientDictionary.getIngredients());
            default:
                return null;
        }

    }
}

