package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;

import com.netcracker.recipeproject.server.model.Command.*;

import java.io.IOException;
import java.util.ArrayList;

public class Store {
    private static Store instance;
    private DishDictionary dishDictionary;
    private IngredientDictionary ingredientDictionary;
    private Developer developer;

    private Store() {
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
        Dish omelette = new Dish(ingredientsOfOmelette, "Омлет", "10 минут");
        dishDictionary.addDish(omelette);


        Ingredient ingredient1 = new Ingredient("cпаггети", "гр");
        Ingredient ingredient2 = new Ingredient("курица", "гр");
        ingredientDictionary.addIngredient(ingredient1);
        ingredientDictionary.addIngredient(ingredient2);
        DishComponent dishComponent1 = new DishComponent(ingredient1, 200);
        DishComponent dishComponent2 = new DishComponent(ingredient2, 300);
        ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
        ingredientsOfOmeletteDuplicate.add(dishComponent1);
        ingredientsOfOmeletteDuplicate.add(dishComponent2);
        Dish paste = new Dish(ingredientsOfOmeletteDuplicate, "Паста с курицей", "20 минут");
        dishDictionary.addDish(paste);
        //////////////////////////////
    }

    public static Store getInstance() throws IOException {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public DishDictionary getDishDictionary() {
        return dishDictionary;
    }


    public IngredientDictionary getIngredientDictionary() {
        return ingredientDictionary;
    }

    public Message doCommand(Message message) {
        CommandEnum flag = message.getFlag();
        switch (flag) { //сделать ENUM
            case SEARCH: //Search
                return developer.searchDish(message);
            case OUTPUT_OF_ALL_DISHES: //output of all dishes
                int i = 0;
                ArrayList<Dish> dishes = new ArrayList<>();
                for (Dish dish: dishDictionary.getAllDishes()){
                    System.out.println("Ингредиент " + ++i +": " + dish.getName());
                    dishes.add(dish);
                }
                return new Message(CommandEnum.OUTPUT_OF_ALL_DISHES, dishes);
            case EDIT_A_DISH: //edit a dish
                return developer.editDish(message);
            case ADD_A_DISH: //add a dish
                return developer.addDish(message);
            case REMOVE_A_DISH: //remove a dish
                return developer.removeDish(message);
            case REMOVE_A_INGREDIENT: //remove a ingredient
                return developer.removeIngredient(message);
            case ADD_AN_INGREDIENT: //add an ingredient
                Object object = message.getObj();
                Ingredient ingredientAdd = (Ingredient) object; //вывести в консоль
                try {
                    ingredientDictionary.addIngredient(ingredientAdd);
                } catch (DuplicateFoundException e) {
                    System.err.println("Обнаружен дупликат");
                    return new Message(CommandEnum.ADDING_A_DUPLICATE_INGREDIENT, ingredientAdd);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                return new Message(CommandEnum.OK, null);
                //return developer.addIngredient(message);
            case EDIT_A_INGREDIENT: // edit a ingredient

            case OUTPUT_OF_ALL_INGREDIENTS: //output of all ingredients
                int j = 0;
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (Ingredient ingredient: ingredientDictionary.getAllIngredients()){
                    System.out.println("Ингредиент " + ++j +": " + ingredient.getName());
                    ingredients.add(ingredient);
                }
                return new Message(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, ingredients);
                //return developer.ingredientsOutput(message);
            default:
                return null;
        }
    }
}

