package com.netcracker.recipeproject.client.model;
 import com.netcracker.recipeproject.library.Dish;
 import com.netcracker.recipeproject.library.Message;

 import java.io.*;
 import java.net.Socket;
 import java.util.ArrayList;

public class InteractionClient {//singletone

    private static final InteractionClient INSTANCE = new InteractionClient();

    private String host;
    private static final int PORT = 2021;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private InteractionClient (){
    }

    public static InteractionClient getInstance(){
        return INSTANCE;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        Message message = (Message) in.readObject();
        if(message.getObj() != null)
            System.out.println(message.getObj().toString());
        return message;
    }

    public void messageRequest(Message message) throws IOException {
        //System.out.println((String) message.getObj());
        out.writeObject(message);
        out.flush();
        //out.close();
    }

    public void process() {
        try{
            socket = new Socket(host, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
