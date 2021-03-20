package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Interaction;
import com.netcracker.recipeproject.library.Message;

import java.net.Socket;

public class ServerInteraction implements Interaction {

    @Override
    public Message getMessage() {
        return null;
    }

    @Override
    public void messageRequest(Message message, Socket socket) {

    }

    @Override
    public void process() {

    }

    @Override
    public void doCommand(Message message) {

    }
}
