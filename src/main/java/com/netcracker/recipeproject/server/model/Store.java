package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.model.Command.*;

import java.util.ArrayList;

public class Store {
    private DishDictionary dishDictionary;
    private IngredientDictionary ingredientDictionary;
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
                new AddIngredientCommand(ingredientDictionary),
                new OutputOfAllIngredientsCommand(ingredientDictionary));

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

    public Message doCommand(Message message) {
        int flag = message.getFlag();
        switch (flag) { //сделать ENUM
            case 0: //Search
                return developer.searchDish(message);
            case 1: //output of all dishes
                System.out.println("Размер списка блюд " + dishDictionary.getAllDishes().size());
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
                //return developer.addIngredient(message);
            case 8: //output of all ingredients
                System.out.println("Размер списка ингредиентов: " + ingredientDictionary.getAllIngredients().size());
                return new Message(1, ingredientDictionary.getAllIngredients());
                //return developer.ingredientsOutput(message);
            default:
                return null;
        }
    }
}

