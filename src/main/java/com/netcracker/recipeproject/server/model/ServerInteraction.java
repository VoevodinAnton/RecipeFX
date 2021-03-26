
package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.library.RecipeIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerInteraction {
    DishDictionary dishDictionary;

    int port;

    public ServerInteraction(int port) {
        this.port = port;
    }

    public Message getMessage() {
        return null;
    }


    public void messageRequest(Socket socket) {

        ObjectInputStream objIn = null;
        ObjectOutputStream objOut = null;
        try {
            objIn = new ObjectInputStream(socket.getInputStream());
            objOut = new ObjectOutputStream(socket.getOutputStream());
            Message messageFromClient;
            while ((messageFromClient = (Message) objIn.readObject()) != null) {
                System.out.println("Получен объект");
                try {

                    doCommand(messageFromClient, objOut);
                    System.err.println(
                            "Отправлен ответ клиенту");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                objOut.flush();
            }
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
            Socket socket;
            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println(
                            "Установлено соединение с клиентом");
                    messageRequest(socket);
                    socket.close();
                } catch (IOException ex) {
                    System.err.println(
                            "Ошибка при установлении связи");
                }
            }
        } catch (IOException ex) {
            System.err.println("Невозможно открыть порт");
        }
    }

    public void doCommand(Message message, ObjectOutputStream objOut) { //перенести в другой класс, в модель
        int flag = message.getFlag();
        Object object = message.getObj();

        switch (flag) { //сделать ENUM
            case 0:
                String searchString = (String) object;
                File libraryOfDishes = new File("LibraryOfOutput/library.bin");

                ArrayList<Dish> dishesToClient = new ArrayList<>(); //список блюд, который отправляется клиенту

                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(libraryOfDishes))) {
                    ArrayList<Dish> dishes = RecipeIO.deserializeDishDictionary(in);
                    for (Dish dish : dishes) {
                        if (dish.contains(searchString)) {
                            dishesToClient.add(dish);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Message messageSearch = new Message(0, dishesToClient);
                    objOut.writeObject(messageSearch);
                    objOut.flush();
                    System.err.println(
                            "Отправлен список всех блюд");
                } catch (Exception ex) {
                    System.err.println("Отправлено исключение");
                    //TODO: create exception
                }
                break;
            case 1:
                try {
                    Message m = new Message(0, null);
                    objOut.writeObject(m);
                    objOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                Dish dishEdit = (Dish) object;
                dishDictionary.setDish(dishEdit);
                break;
            case 3:
                Dish dishAdd = (Dish) object;
                dishDictionary.addDish(dishAdd);
                break;

        }

    }

    public static void main(String[] args) { //можно создать класс с константами
        int port = 2021; //added public static constant
        ServerInteraction serverInteraction = new ServerInteraction(port);
        serverInteraction.process();
    }
}

