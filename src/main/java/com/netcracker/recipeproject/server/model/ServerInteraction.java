
package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.library.RecipeIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerInteraction {

    int port;

    public ServerInteraction(int port) {
        this.port = port;
    }

    public Message getMessage() {
        return null;
    }


    public void messageRequest(Socket socket) {
        try (ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream())) {
            Message messageFromClient;
            while ((Boolean) objIn.readObject()) { //он еще вот здесь ругается, на самом делене понимаю, зачем тебе эта строчка
                messageFromClient = (Message) objIn.readObject();
                try {
                    doCommand(messageFromClient, objOut);
                    System.out.println(
                            "Отправлен ответ клиенту");
                } catch (Exception ex) {
                    objOut.writeObject(ex);
                    System.out.println("Отправлено исключение");
                }
                objOut.flush();
            }
        } catch (IOException ex) {
            System.err.println(
                    "Ошибка ввода/вывода при работе с клиентом");
        } catch (ClassNotFoundException ex) {
            System.err.println("Неизвестный класс в запросе");
        }
    }


    public void process() {
        try (ServerSocket serverSocket = new ServerSocket(port)){

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

    public void doCommand(Message message, ObjectOutputStream objOut) {
        int flag = message.getFlag();
        Object object = message.getObj();

        switch (flag) {
            case 0:
                String searchString = (String) object;
                File libraryOfDishes = new File("LibraryOfOutput/library.bin");

                ArrayList<Dish> dishesToClient = new ArrayList<>(); //список блюд, который отправляется клиенту

                try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(libraryOfDishes))){
                    ArrayList<Dish> dishes = RecipeIO.deserializeDishDictionary(in);
                    for (Dish dish : dishes) {
                        if (dish.contains(searchString)) {
                            dishesToClient.add(dish);
                        }
                    }
                } catch (IOException | ClassNotFoundException e){
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

        }

    }

    public static void main(String[] args) {
        int port = 2021;
        ServerInteraction s = new ServerInteraction(port);
        s.process();
    }
}

