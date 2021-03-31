package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.netcracker.recipeproject.client.model.Dictionary;
import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController{

        @FXML
        private Button searchButton;

        @FXML
        private Button allDishButton;

        @FXML
        private TextField searchField;

        @FXML
        private ListView<Dish> dishList;

        @FXML
        private Button sortButton;

        @FXML
        private Button addDishButton;

        @FXML
        private Button ingredientsButton;

        @FXML
        private ObservableList<Dish> dishObservableArray;


        @FXML
        public void initialize(){
            dishList.setItems(FXCollections.observableArrayList(Dictionary.getDishes()));
            dishList.setCellFactory(studentListView -> new ListCellController());

            searchButton.setOnAction(actionEvent -> {
                String search = searchField.getText();
                if(!search.equals("")){
                    System.out.println("*"+ search);
                    try {
                        InteractionClient client = InteractionClient.getInstance();
                        Message messageToServer = new Message(0, search);
                        client.messageRequest(messageToServer);
                        Message messageFromServer = client.getMessage();
                        dishList.getItems().clear();
                        Dictionary.setDishes((ArrayList<Dish>)messageFromServer.getObj());
                        dishList.setItems(FXCollections.observableArrayList(Dictionary.getDishes()));
                        dishList.setCellFactory(studentListView -> new ListCellController());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            ingredientsButton.setOnAction(actionEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientsFrame.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            addDishButton.setOnAction(actionEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/addDishFrame.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(this.addDishButton.getScene().getWindow());
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            allDishButton.setOnAction(actionEvent -> {
                try {
                    InteractionClient client = InteractionClient.getInstance();
                    Message messageToServer = new Message(1, null);
                    client.messageRequest(messageToServer);
                    Message messageFromServer = client.getMessage();
                    dishList.getItems().clear();
                    Dictionary.setDishes((ArrayList<Dish>)messageFromServer.getObj());
                    dishList.setItems(FXCollections.observableArrayList(Dictionary.getDishes()));
                    dishList.setCellFactory(studentListView -> new ListCellController());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
}



