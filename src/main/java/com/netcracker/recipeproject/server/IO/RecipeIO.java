package com.netcracker.recipeproject.server.IO;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;

import java.io.*;
import java.util.ArrayList;

public class RecipeIO {

    public static void serializeDishDictionary(BufferedOutputStream stream, ArrayList<Dish> dishes) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(dishes);
        out.flush();
    }

    public static void serializeIngredientDictionary(BufferedOutputStream stream, ArrayList<Ingredient> ingredients) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(ingredients);
        out.flush();
    }

    public static ArrayList<Dish> deserializeDishDictionary(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (ArrayList<Dish>) new ObjectInputStream(stream).readObject();
    }

    public static ArrayList<Ingredient> deserializeIngredientDictionary(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (ArrayList<Ingredient>) new ObjectInputStream(stream).readObject();
    }

    public static boolean isFileEmpty(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine() == null;
    }

}
