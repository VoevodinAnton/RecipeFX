package com.netcracker.recipeproject.server.IO;

import com.netcracker.recipeproject.library.Dish;

import java.io.*;
import java.util.ArrayList;

public class RecipeIO {

    public static void serializeDishDictionary(BufferedOutputStream stream, ArrayList<Dish> dishes) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(dishes);
        out.flush();
    }

    public static ArrayList<Dish> deserializeDishDictionary(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        return (ArrayList<Dish>) new ObjectInputStream(stream).readObject();
    }

}
