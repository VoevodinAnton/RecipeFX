package com.netcracker.recipeproject.server.IO;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.model.Store;

import java.io.*;
import java.util.ArrayList;

public class DishDictionarySerialization {


    public static void main(String[] args) throws IOException {

        //////////////////////////////////////
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient chicken = new Ingredient("курица", "кг");
        Store.getInstance().addIngredient(egg);
        Store.getInstance().addIngredient(chicken);
        DishComponent eggComponent = new DishComponent(egg, 3);
        DishComponent chickenComponent = new DishComponent(chicken, 1);
        ArrayList<DishComponent> ingredients = new ArrayList<>();
        ingredients.add(eggComponent);
        ingredients.add(chickenComponent);
        Dish omlet = new Dish(ingredients, "omlet", "10 минут");
        Store.getInstance().addDish(omlet);
        ////////////////////////////
        File fileDishes = new File("LibraryOfOutput/serialized dish dictionary.bin");

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileDishes))) {
            int i = 0;
            ArrayList<Dish> dishes = new ArrayList<>();
            for (Dish dish : Store.getInstance().getAllDishes()) {
                System.out.println("Блюдо " + ++i + ": " + dish.getName());
                dishes.add(dish);
            }
            System.out.println(Store.getInstance().getAllDishes().size());
            RecipeIO.serializeDishDictionary(out, dishes);

        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileDishes))) {

            ArrayList<Dish> deserializedDishes = RecipeIO.deserializeDishDictionary(in);
            System.out.println(deserializedDishes.size());
            int i = 0;
            for (Dish dish : deserializedDishes) {
                System.out.println("Блюдо " + ++i + ": " + dish.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
