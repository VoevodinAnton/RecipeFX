package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.IO.RecipeIO;
import com.netcracker.recipeproject.server.model.Store;
import com.netcracker.recipeproject.server.model.utils.Constants;

import java.io.*;
import java.util.ArrayList;

public class TestClass {



    public static void main(String[] args) throws IOException {
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient chicken = new Ingredient("курица", "гр");
        DishComponent eggComponent = new DishComponent(egg, 4);
        DishComponent chickenComponent = new DishComponent(chicken, 1);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(eggComponent);
        ingredientsOfOmelette.add(chickenComponent);
        Dish omelette = new Dish(ingredientsOfOmelette, "Омлет", "10 минут");


        Ingredient rice = new Ingredient("рис", "гр");
        Ingredient meat = new Ingredient("мясо", "гр");
        DishComponent riceComponent = new DishComponent(rice, 4);
        DishComponent meatComponent = new DishComponent(meat, 1);
        ArrayList<DishComponent> ingredientsOfRwM = new ArrayList<>();
        ingredientsOfRwM.add(riceComponent);
        ingredientsOfRwM.add(meatComponent);
        Dish riceWithMeat = new Dish(ingredientsOfRwM, "Рис с мясом", "10 минут");

        Ingredient noodles = new Ingredient("лапша", "шт");
        Ingredient cheese = new Ingredient("сыр", "кг");
        DishComponent noodlesComponent = new DishComponent(noodles, 1);
        DishComponent cheeseComponent = new DishComponent(cheese, 1);
        ArrayList<DishComponent> ingredientsOfNwCh = new ArrayList<>();
        ingredientsOfNwCh.add(noodlesComponent);
        ingredientsOfNwCh.add(cheeseComponent);
        Dish noodlesWithCheese = new Dish(ingredientsOfNwCh, "Лапша с сыром", "15 минут");



        Store.getInstance().addIngredient(egg);
        Store.getInstance().addIngredient(chicken);
        Store.getInstance().addDish(omelette);

        Store.getInstance().addIngredient(rice);
        Store.getInstance().addIngredient(meat);
        Store.getInstance().addDish(riceWithMeat);

        Store.getInstance().addIngredient(noodles);
        Store.getInstance().addIngredient(cheese);
        Store.getInstance().addDish(noodlesWithCheese);

        File fileDishes = new File("LibraryOfDishes/" + Constants.fileNameDishes);
        File fileIngredients = new File("LibraryOfIngredients/" + Constants.fileNameIngredients);
        try (BufferedOutputStream outD = new BufferedOutputStream(new FileOutputStream(fileDishes)); BufferedOutputStream outI = new BufferedOutputStream(new FileOutputStream(fileIngredients))) {
            int i = 0;
            ArrayList<Dish> dishes = new ArrayList<>();
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (Dish dish : Store.getInstance().getAllDishes()) {
                System.out.println("Блюдо " + ++i + ": " + dish.getName());
                dishes.add(dish);
            }
            for (Ingredient ingredient : Store.getInstance().getAllIngredients()) {
                System.out.println("Ингредиент " + ++i + ": " + ingredient.getName());
                ingredients.add(ingredient);
            }
            RecipeIO.serializeDishDictionary(outD, dishes);
            RecipeIO.serializeIngredientDictionary(outI, ingredients);
        } catch (IOException e) {
            e.printStackTrace();
        }



        try (BufferedInputStream inD = new BufferedInputStream(new FileInputStream(fileDishes)); BufferedInputStream inI = new BufferedInputStream(new FileInputStream(fileIngredients))) {
            ArrayList<Dish> deserializedDishes = RecipeIO.deserializeDishDictionary(inD);
            for (Dish dish : deserializedDishes) {
                if (!Store.getInstance().getAllDishes().contains(dish)) {
                    Store.getInstance().addDish(dish);
                }
            }
            ArrayList<Ingredient> deserializedIngredients = RecipeIO.deserializeIngredientDictionary(inI);
            for (Ingredient ingredient : deserializedIngredients) {
                if (!Store.getInstance().getAllIngredients().contains(ingredient)) {
                    Store.getInstance().addIngredient(ingredient);
                }
            }
            int i = 0;
            for (Dish dish : deserializedDishes) {
                System.out.println("Блюдо " + ++i + ": " + dish.getName());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

