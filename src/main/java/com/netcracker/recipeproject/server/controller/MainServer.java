package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.model.DishDictionary;
import com.netcracker.recipeproject.server.model.Storage;
import com.netcracker.recipeproject.server.model.Store;
import com.netcracker.recipeproject.server.model.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

public class MainServer {
    public static void main(String[] args) throws IOException { //можно создать класс с константами

        try {
            ServerInteraction.getInstance().startServer();
        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера");
        }
    }
}
