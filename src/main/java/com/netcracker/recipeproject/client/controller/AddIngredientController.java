package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
             if(nameField.getText().equals("")) {
                 errorLabel.setText("");
                 String name = nameField.getText();
                 if(unitField.getText().equals("")){
                     errorLabel.setText("");
                     String unit = unitField.getText();
                     Ingredient ingredient = new Ingredient(name, unit);
                     try {
                         InteractionClient client = InteractionClient.getInstance();
                         Message messageOut = new Message(6, ingredient);
                         client.messageRequest(messageOut);
                         //TODO: принимать ответное сообщение об успешном добавлении или о дубликате и обрабатывать
                     }catch (IOException e)
                     {
                         e.printStackTrace();
                     }
                 }
                 else {
                     errorLabel.setText("Введите единицу измерения");
                 }
             }
             else{
                 errorLabel.setText("Введите название");
             }
         });

    }
}
