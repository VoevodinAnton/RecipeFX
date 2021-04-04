package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngredientsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView refreshButton;

    @FXML
    private Button addButton;

    @FXML
    private ListView<Ingredient> ingredientsList;

    @FXML
    void initialize() {
        try {
            InteractionClient client = InteractionClient.getInstance();
            Message messageToServer = new Message(8, null);
            client.messageRequest(messageToServer);
            Message messageFromServer = client.getMessage();
            ingredientsList.getItems().clear();
            ingredientsList.setItems(FXCollections.observableArrayList((ArrayList<Ingredient>) messageFromServer.getObj()));
            ingredientsList.setCellFactory(ingredientListView -> new IngredientCellController());

            refreshButton.setOnMouseClicked(mouseEvent -> {
                try {
                    InteractionClient client2 = InteractionClient.getInstance();
                    Message messageOut = new Message(8, null);
                    client2.messageRequest(messageOut);
                    Message messageIn = client2.getMessage();
                    System.out.println("Принят список ингредиентов. Размер списка: " + ((ArrayList<Ingredient>) messageIn.getObj()).size());
                    ingredientsList.getItems().clear();
                    ingredientsList.setItems(FXCollections.observableArrayList((ArrayList<Ingredient>) messageIn.getObj()));
                    ingredientsList.setCellFactory(ingredientListView -> new IngredientCellController());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            addButton.setOnAction(actionEvent -> {
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
                stage.initOwner(this.addButton.getScene().getWindow());
                stage.showAndWait();
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
