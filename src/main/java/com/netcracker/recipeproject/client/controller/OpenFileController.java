package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OpenFileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button downloadButton;

    @FXML
    private Button uploadButton;

    @FXML
    void initialize() {
        downloadButton.setOnAction(actionEvent -> {
            Message response = Messaging.execute(CommandEnum.UPLOAD_TO_FILE, null);
            if(response.getFlag() == CommandEnum.OK){
                Stage stage = (Stage) downloadButton.getScene().getWindow();
                stage.close();
            }

        });

        uploadButton.setOnAction(actionEvent -> {

            Message response = Messaging.execute(CommandEnum.UPLOAD_FROM_FILE, null);
            if(response.getFlag() == CommandEnum.OK){
                //обновляем данные списка блюд
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrimaryController controller = loader.getController();
                Message message = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null);
                controller.setDishObservableList((List<Dish>)message.getObj());

                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientsFrame.fxml"));
                try {
                    loader2.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                IngredientsController controller2 = loader2.getController();
                Message message2 = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
                controller2.setIngredientObservableList((List<Ingredient>)message2.getObj());
                Stage stage = (Stage) downloadButton.getScene().getWindow();
                stage.close();
            }
        });

    }
}
