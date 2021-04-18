package com.netcracker.recipeproject.client.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteIngredientController {
    private static Ingredient ingredient;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private Label messageLabel;

    @FXML
    void initialize() {
        yesButton.setOnAction(actionEvent -> {
                Message messageIn = Messaging.execute(CommandEnum.REMOVE_A_INGREDIENT, ingredient);
                if(messageIn.getFlag() == CommandEnum.OK) {
                    Stage stageIp = (Stage) yesButton.getScene().getWindow();
                    stageIp.close();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientsFrame.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    IngredientsController controller = loader.getController();
                    Message message = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
                    controller.setIngredientObservableList((List<Ingredient>)message.getObj());

                }
                else {
                    messageLabel.setText("Не удалось удалить блюдо. Попробуйте позже");
                }
        });

        noButton.setOnAction(actionEvent -> {
            Stage stageIp = (Stage) yesButton.getScene().getWindow();
            stageIp.close();
        });

    }

    public static void setIngredient(Ingredient ingredient1){
        ingredient = ingredient1;
    }
}

