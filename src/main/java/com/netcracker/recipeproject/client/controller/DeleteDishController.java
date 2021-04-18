package com.netcracker.recipeproject.client.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteDishController {
    private static Dish dish;

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
                Message messageOut = new Message(CommandEnum.REMOVE_A_DISH, dish);
                client.messageRequest(messageOut);
                Message messageIn = client.getMessage();
                if(messageIn.getFlag() == CommandEnum.OK) {
                    Stage stageIp = (Stage) yesButton.getScene().getWindow();
                    stageIp.close();
                }
                //обновляем данные списка блюд
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
                loader.load();
                PrimaryController controller = loader.getController();
                Message response = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null);
                controller.setDishObservableList((List<Dish>)response.getObj());
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        noButton.setOnAction(actionEvent -> {
            Stage stageIp = (Stage) yesButton.getScene().getWindow();
            stageIp.close();
        });

    }

    public static void setDish(Dish dish1){
        dish = dish1;
    }
}

