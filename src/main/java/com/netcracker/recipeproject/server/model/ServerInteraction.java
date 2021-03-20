package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInteraction{
    int port;

    public ServerInteraction(int port){
        this.port = port;
    }

    public Message getMessage() {
        return null;
    }

    public void messageRequest(Message message, Socket socket) {

    }


    public void messageRequest(Socket socket) {
        ObjectOutputStream objOut = null;
        ObjectInputStream objIn = null;



    }

    public void process() {
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен");
            Socket socket;
            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println(
                            "Установлено соединение с клиентом");
                    messageRequest(socket);
                } catch (IOException ex) {
                    System.err.println(
                            "Ошибка при установлении связи");
                }
            }
        } catch (IOException ex) {
            System.err.println("Невозможно открыть порт");
        }


    }

    public void doCommand(Message message) {

    }
}
