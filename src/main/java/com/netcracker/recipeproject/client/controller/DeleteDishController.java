package com.netcracker.recipeproject.client.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteDishController {
    private Dish dish;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    void initialize() {
        yesButton.setOnAction(actionEvent -> {
            try {
                InteractionClient client = InteractionClient.getInstance();
                Message messageOut = new Message(4, dish);
                client.messageRequest(messageOut);
                Message messageIn = client.getMessage();
                //сделать условие, если удаление прошло успешно
                Stage stageIp = (Stage) yesButton.getScene().getWindow();
                stageIp.close();
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        noButton.setOnAction(actionEvent -> {
            Stage stageIp = (Stage) yesButton.getScene().getWindow();
            stageIp.close();
        });

    }

    public void setDish(Dish dish){
        this.dish = dish;
    }
}

