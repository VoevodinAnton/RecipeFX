package com.netcracker.recipeproject.client.model;
 import com.netcracker.recipeproject.library.Message;

 import java.io.IOException;
 import java.net.Socket;
 import java.net.UnknownHostException;

public class InteractionClient {

    private String host;
    private int port;
    Socket s;

    public InteractionClient (String host, int port){
        this.host = host;
        this.port = port;
        s = null;
    }


    public Message getMessage() {
        return null;
    }


    public void messageRequest(Message message) {

    }

    public void process() {
        try{
            s = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doCommand(Message message) {

    }
}
