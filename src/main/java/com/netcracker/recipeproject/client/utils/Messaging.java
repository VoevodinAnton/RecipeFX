package com.netcracker.recipeproject.client.utils;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Message;

import java.io.IOException;

public class Messaging {

    public static Message execute(CommandEnum commandEnum, Object obj){
        Message message = null;
        try {
            InteractionClient client = InteractionClient.getInstance();
            Message messageOut = new Message(commandEnum, obj);
            client.messageRequest(messageOut);
            message = client.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
}
