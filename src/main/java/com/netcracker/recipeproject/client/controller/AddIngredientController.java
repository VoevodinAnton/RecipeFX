package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Checks;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddIngredientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField unitField;

    @FXML
    private Button addButton;

    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
         addButton.setOnAction(actionEvent -> {
             String error = Checks.checkingIngredient(nameField, unitField);
             if(error.equals("")) {
                 String name = nameField.getText();
                 String unit = unitField.getText();
                 Ingredient ingredient = new Ingredient(name, unit);
                 Message response = Messaging.execute(CommandEnum.ADD_AN_INGREDIENT, ingredient);
                 if (response.getFlag() == CommandEnum.OK) {
                     Stage stageIp = (Stage) addButton.getScene().getWindow();
                     stageIp.close();
                 }
             }
             errorLabel.setText(error);
         });
    }
}
