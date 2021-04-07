package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.server.model.utils.Constants;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) { //можно создать класс с константами
        try {
            ServerInteraction.getInstance().startServer();
        } catch (IOException e) {
            System.out.println("Ошибка запуска сервера");
        }
    }
}
