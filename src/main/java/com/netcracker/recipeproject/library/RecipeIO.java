package com.netcracker.recipeproject.library;

import com.netcracker.recipeproject.server.model.DishDictionary;

import java.io.*;
import java.util.ArrayList;

public class RecipeIO {
    private RecipeIO() {
        throw new UnsupportedOperationException("Unavailable operation");
    }

    public static void serializeDishDictionary(BufferedOutputStream stream, ArrayList<Dish> dishes) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(dishes);
        out.flush();
    }

    public static ArrayList<Dish> deserializeDishDictionary(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (ArrayList<Dish>) new ObjectInputStream(stream).readObject();
    }


}
