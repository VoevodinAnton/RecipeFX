package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.IO.RecipeIO;
import com.netcracker.recipeproject.server.model.Store;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class M {

    public static void main(String[] args) throws IOException {

        String fileName = "serialized dish dictionary2.bin";
        File fileDishes = new File("LibraryOfDishes/" + fileName);
        File fileIngredients = new File("LibraryOfIngredients/" + "Ing" + fileName);

        if (fileDishes.exists() && fileIngredients.exists()){
            try (BufferedInputStream inD = new BufferedInputStream(new FileInputStream(fileDishes)); BufferedInputStream inI = new BufferedInputStream(new FileInputStream(fileIngredients))) {
                ArrayList<Dish> deserializedDishes = RecipeIO.deserializeDishDictionary(inD);
                for (Dish dish: deserializedDishes){
                    if (!Store.getInstance().getAllDishes().contains(dish)){
                        Store.getInstance().addDish(dish);
                    }
                }
                ArrayList<Ingredient> deserializedIngredients = RecipeIO.deserializeIngredientDictionary(inI);
                for (Ingredient ingredient: deserializedIngredients){
                    if (!Store.getInstance().getAllIngredients().contains(ingredient)){
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
        } else {
            fileDishes.createNewFile();
            fileIngredients.createNewFile();
        }
    }
}

