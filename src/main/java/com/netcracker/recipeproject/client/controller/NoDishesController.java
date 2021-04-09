package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NoDishesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addDishButton;

    @FXML
    private Button addIngredientButton;

    @FXML
    void initialize() {
            try{
                InteractionClient client = InteractionClient.getInstance();
                Message messageOut = new Message(CommandEnum.OUTPUT_OF_ALL_DISHES, null);//get Dish Dictionary
                client.messageRequest(messageOut);
                Message messageIn = client.getMessage();
                if(messageIn.getObj() != null){
                    Stage stageIp = (Stage) addDishButton.getScene().getWindow();
                    stageIp.close();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
            addDishButton.setOnAction(actionEvent -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/addDishFrame.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(this.addDishButton.getScene().getWindow());
                stage.showAndWait();
            });

        addIngredientButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/addIngredientFrame.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.addIngredientButton.getScene().getWindow());
            stage.showAndWait();
        });
    }
}
