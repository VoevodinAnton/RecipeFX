
package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.model.Store;
import com.netcracker.recipeproject.server.model.DishDictionary;
import com.netcracker.recipeproject.server.model.IngredientDictionary;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerInteraction {
    Store store;
    int port;


    public ServerInteraction(int port) {
        this.port = port;
        store = new Store();

    }

    public Message getMessage() {
        return null;
    }


    public void messageRequest(Socket socket) {

        ObjectInputStream objIn;
        ObjectOutputStream objOut;
        try {
            objIn = new ObjectInputStream(socket.getInputStream());
            objOut = new ObjectOutputStream(socket.getOutputStream());
            Message messageFromClient = (Message) objIn.readObject();
            //while ((messageFromClient = (Message) objIn.readObject()) != null) {
            System.out.println("Получен объект");
            try {

                store.doCommand(messageFromClient, objOut);
                System.out.println(
                        "Отправлен ответ клиенту");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            objOut.flush();
            //}
            objIn.close();
            objOut.close();

        } catch (IOException ex) {
            //System.err.println("Ошибка ввода/вывода при работе с клиентом");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Неизвестный класс в запросе");
        }
    }


    public void process() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Сервер запущен");
            Socket socket = null;
            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println(
                            "Установлено соединение с клиентом");
                    messageRequest(socket);

                } catch (IOException ex) {
                    System.err.println(
                            "Ошибка при установлении связи");
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (IOException ex) {
            System.err.println("Невозможно открыть порт");
        }
    }


    public static void main(String[] args) { //можно создать класс с константами
        int port = 2021; //add public static constant
        ServerInteraction serverInteraction = new ServerInteraction(port);
        serverInteraction.process();
    }
}

