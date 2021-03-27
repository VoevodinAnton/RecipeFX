package com.netcracker.recipeproject.client.model;
 import com.netcracker.recipeproject.client.view.App;
 import com.netcracker.recipeproject.library.Dish;
 import com.netcracker.recipeproject.library.Message;

 import java.io.IOException;
 import java.io.ObjectInput;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.net.InetSocketAddress;
 import java.net.Socket;
 import java.net.UnknownHostException;
 import java.util.ArrayList;

public class InteractionClient {//singletone

    private static final InteractionClient INSTANCE = new InteractionClient();

    private String host;
    private static final int PORT = 2021;
    private Socket socket;

    private InteractionClient (){
    }

    public static InteractionClient getInstance(){
        return INSTANCE;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Message message = (Message) in.readObject();
        //in.close();
        return message;
    }


    public void messageRequest(Message message) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(message);
        System.out.println((String)message.getObj());
        out.flush();
        //out.close();
    }

    public void process() {
        try{
            socket = new Socket(host, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doCommand(Message message) throws IOException {
        int flag = message.getFlag();
        Object obj = message.getObj();
        switch (flag){
            case 0: //пришел список блюд
                DishDictionary.dishes = (ArrayList<Dish>)obj;
                System.out.println(DishDictionary.dishes.get(0).getName());
                break;
            case 1: //пришел список ингредиентов
                break;
            case 2: //пришло сообщение о добавлении дубликата блюда
                break;
            case 3: //пришло сообщение о добавлении дубликата ингредиента
                break;
            case 4: //пришло сообщение о том, что есть блюда, в которых присутствует удаляемый ингредиент
        }

    }
}
