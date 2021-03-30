package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.Store;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerFacade {

    private Socket socket;
    ObjectInputStream objIn;
    ObjectOutputStream objOut;
    private Store store;

    public ServerFacade(Socket socket) throws IOException {
        this.socket = socket;
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());
        store = new Store();
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        return (Message) objIn.readObject();
    }

    public void messageRequest(Message message) {
        try {
            store.doCommand(message, objOut);
            System.out.println("Отправлен ответ клиенту");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() throws IOException {
        socket.close();
        objIn.close();
        objOut.close();
    }
}
