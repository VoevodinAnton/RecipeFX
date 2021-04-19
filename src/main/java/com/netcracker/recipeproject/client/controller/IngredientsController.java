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
import javafx.collections.ObservableList;
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
    private Button addButton;

    @FXML
    private ImageView backButton;

    @FXML
    private ListView<Ingredient> ingredientsList;

    private static ObservableList<Ingredient> ingredientObservableList = FXCollections.observableArrayList();

    public void setIngredientObservableList(List<Ingredient> list) {
        ingredientsList.getItems().clear();
        List<Ingredient> ingredientList = ingredientObservableList;
        ingredientObservableList.removeAll(ingredientList);
        ingredientObservableList.addAll(list);
    }

    @FXML
    void initialize() {
            Message response = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
            setIngredientObservableList((List<Ingredient>)response.getObj());
            ingredientsList.setItems(ingredientObservableList);
            ingredientsList.setCellFactory(ingredientListView -> new IngredientCellController());

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

                backButton.setOnMouseClicked(mouseEvent -> {
                    Stage stageI = (Stage) backButton.getScene().getWindow();
                    stageI.close();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));

                    Parent root = null;
                    try {
                        root = loader.load();
                        PrimaryController controller = loader.getController();
                        Message message = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null);
                        controller.setDishObservableList((List<Dish>) message.getObj());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                });

    }
}
