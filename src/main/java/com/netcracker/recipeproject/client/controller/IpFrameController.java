package com.netcracker.recipeproject.client.controller;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class IpFrameController {

    @FXML
    private TextField ipField;

    @FXML
    private Button okeyButton;

    @FXML
    void initialize(){
        okeyButton.setOnAction(actionEvent -> {
            String host;
            host = ipField.getText();
            InteractionClient client = InteractionClient.getInstance();
            client.setHost(host);
            client.process();
            System.out.println("Подключен к серверу");

            Message messageOut = new Message(1, null);//отправляем сообщение о выводе всего списка блюд
            try {
                client.messageRequest(messageOut);//отправляем сообщение
                Message messageIn = client.getMessage();//принимаем ответное сообщение с сервера
                client.doCommand(messageIn);//выполняем соответствующую команду
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }



        });
    }

}
