package com.netcracker.recipeproject.server.controller;

import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.model.Developer;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerFacade implements Closeable {
    private Developer developer;
    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    public ServerFacade(Socket socket) throws IOException {
        this.developer = new Developer();
        this.socket = socket;
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        return (Message) objIn.readObject();
    }

    public void messageRequest(Message message) {
        try {
            Message messageToClient = developer.doCommand(message);
            if(messageToClient.getObj() != null)
                System.out.println(messageToClient.getObj().toString());
            objOut.writeObject(messageToClient);
            objOut.flush();
            System.out.println("Отправлен ответ клиенту");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        objIn.close();
        objOut.close();
        socket.close();
    }
}
