
package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.model.Store;
import com.netcracker.recipeproject.server.model.utils.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerInteraction {
    private static ServerInteraction instance;
    private final ExecutorService executorService;

    private ServerInteraction() {
        executorService = Executors.newFixedThreadPool(2);
    }

    public static ServerInteraction getInstance() throws IOException {
        if (instance == null) {
            instance = new ServerInteraction();
        }
        return instance;
    }


    public void startServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT); BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created, command console reader for listen to server commands");
            while (true) {
                Socket socket = serverSocket.accept();
                if (br.ready()) {
                    System.out.println("Main Server found any messages in channel, let's look at them.");
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Main Server initiate exiting...");
                        serverSocket.close();
                        break;
                    }
                }
                executorService.execute(new MonoThreadClientHandler(socket));
                System.out.print("Connection accepted.");
            }
            executorService.shutdown();
        } catch (IOException e){
            System.err.println("Ошибка установления связи с клиентом");
        }

        /*
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

         */
    }
}
