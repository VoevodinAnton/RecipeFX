package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class IngredientsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView backButton;

    @FXML
    private Button addButton;

    @FXML
    private ListView<String> ingredientsList;

    @FXML
    void initialize() {
        try {
            InteractionClient client = InteractionClient.getInstance();
            Message messageToServer = new Message(8, null);
            client.messageRequest(messageToServer);
            Message messageFromServer = client.getMessage();
            ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) messageFromServer.getObj();
            ArrayList<String> ingredientsString = new ArrayList<>();
            for(Ingredient ingredient : ingredients) {
                ingredientsString.add(ingredient.getName() + "\n" + "измеряется в " + ingredient.getUnit());
            }
            ingredientsList.setItems(FXCollections.observableArrayList(ingredientsString));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
