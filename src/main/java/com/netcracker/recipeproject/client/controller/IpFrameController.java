package com.netcracker.recipeproject.client.controller;

import com.netcracker.recipeproject.client.model.Dictionary;
import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IpFrameController {

    @FXML
    private TextField ipField;

    @FXML
    private Button okeyButton;

    @FXML
    public void initialize(){
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
                Dictionary.setDishes((ArrayList<Dish>)messageIn.getObj());//выполняем соответствующую команду
                Stage stageIp = (Stage) okeyButton.getScene().getWindow();
                stageIp.close();
                if(messageIn.getObj() != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                else{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/noDishesFrame.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
    }

}
