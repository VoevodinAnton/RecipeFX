package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
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

        ArrayList<String> results = new ArrayList();
        File[] files = new File("LibraryOfOutput").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }

        }
        System.out.println(results);


        String fileName = "serialized dish dictionary.bin";
        File fileDishes = new File("LibraryOfOutput/" + fileName);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileDishes))) {
            ArrayList<Dish> deserializedDishes = RecipeIO.deserializeDishDictionary(in);
            for (Dish dish: deserializedDishes){
                if (!Store.getInstance().getAllDishes().contains(dish)){
                    Store.getInstance().addDish(dish);
                }
            }
            int i = 0;
            for (Dish dish : deserializedDishes) {
                System.out.println("Блюдо " + ++i + ": " + dish.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(Store.getInstance().getAllDishes().size());
    }
}

