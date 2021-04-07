package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.Message;

import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable{

    private static Socket socket;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.socket = client;
    }
    @Override
    public void run() {
        try {
            ServerFacade serverFacade = new ServerFacade(socket);
            Message clientMessage;
            while (!socket.isClosed()) {
                clientMessage = serverFacade.getMessage();
                if (clientMessage.getFlag() == 10) {
                    System.out.println("Client initialize connections suicide ...");
                    Thread.sleep(3000);
                    break;
                }
                serverFacade.messageRequest(clientMessage);
            }
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            serverFacade.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
