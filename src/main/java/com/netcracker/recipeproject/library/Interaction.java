package com.netcracker.recipeproject.library;

import java.net.Socket;

public interface Interaction {
    Message getMessage();
    void messageRequest(Message message, Socket socket);
    void process();
    void doCommand(Message message);
}
