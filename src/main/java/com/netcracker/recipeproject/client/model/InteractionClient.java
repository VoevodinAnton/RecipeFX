package com.netcracker.recipeproject.client.model;
 import com.netcracker.recipeproject.client.view.App;
 import com.netcracker.recipeproject.library.Dish;
 import com.netcracker.recipeproject.library.Message;

 import java.io.IOException;
 import java.io.ObjectInput;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.net.Socket;
 import java.net.UnknownHostException;
 import java.util.ArrayList;

public class InteractionClient {//singletone

    private static final InteractionClient INSTANCE = new InteractionClient();

    private String host;
    private static final int PORT = 2021;
    private Socket s;

    private InteractionClient (){
    }

    public static InteractionClient getInstance(){
        return INSTANCE;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        Message message = (Message) in.readObject();
        return message;
    }


    public void messageRequest(Message message) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        out.writeObject(message);
        out.flush();
        out.close();
    }

    public void process() {
        try{
            s = new Socket(host, PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doCommand(Message message) throws IOException {
        int flag = message.getFlag();
        Object obj = message.getObj();
        switch (flag){
            case 0: //пришел список блюд
                ArrayList<Dish> dishArrayList = (ArrayList<Dish>)obj;
                App.setRoot("/com/netcracker/recipeproject/FXML/primary.fxml");
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
