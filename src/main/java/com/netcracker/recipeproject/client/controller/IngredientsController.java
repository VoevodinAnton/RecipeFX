package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
            Message response = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
            ingredientsList.getItems().clear();
            ingredientsList.setItems(FXCollections.observableArrayList((List<Ingredient>) response.getObj()));
            ingredientsList.setCellFactory(ingredientListView -> new IngredientCellController());

            refreshButton.setOnMouseClicked(mouseEvent -> {
                    Message messageIn = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
                    System.out.println("Принят список ингредиентов. Размер списка: " + ((List<Ingredient>) messageIn.getObj()).size());
                    ingredientsList.getItems().clear();
                    ingredientsList.setItems(FXCollections.observableArrayList((List<Ingredient>) messageIn.getObj()));
                    ingredientsList.setCellFactory(ingredientListView -> new IngredientCellController());
            });

        ingredientsList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Ingredient>() {
                    @Override
                    public void changed(ObservableValue<? extends Ingredient> observableValue, Ingredient ingredient, Ingredient t1) {
                        EditIngredientController.setIngredient(t1);
                        DeleteIngredientController.setIngredient(t1);
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


    }
}
