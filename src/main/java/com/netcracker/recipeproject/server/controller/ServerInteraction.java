
package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.model.Store;
import com.netcracker.recipeproject.server.model.utils.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerInteraction {
    public static ServerInteraction instance;
    private ExecutorService executorService;
    Store store;
    ServerSocket serverSocket;


    public ServerInteraction() throws IOException {
        serverSocket = new ServerSocket(Constants.PORT);
        store = new Store();
        executorService = Executors.newCachedThreadPool();

    }

    public static ServerInteraction getInstance() throws IOException {
        if (instance == null) {
            instance = new ServerInteraction();
        }
        return instance;
    }


    public void startServer() {

        for (int i = 0; i < Constants.COUNT_CLIENTS; i++) {
            System.out.println("Thread #" + i + "starts");
            executorService.submit(() -> {
                try {


                    while (true) {
                        System.out.println("Сервер запущен");
                        Socket socket = serverSocket.accept();
                        System.out.println("Установлено соединение с клиентом");
                        ServerFacade serverFacade = new ServerFacade(socket);
                        Message clientMessage;
                        while (true) {
                            clientMessage = serverFacade.getMessage();
                            serverFacade.messageRequest(clientMessage);
                        }


                    }

                } catch (Exception e) {
                    System.out.println("Ошибка установления связи с клиентом");
                }
            });
        }
    }
}

