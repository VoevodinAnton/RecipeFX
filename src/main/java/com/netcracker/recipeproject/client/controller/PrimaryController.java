package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.DishDictionary;
import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.view.App;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

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
        private Button allDishButton2;

        @FXML
        private Button allDishButton21;

        private ObservableList<Dish> dishObservableArray;

        public PrimaryController(){
            dishObservableArray = FXCollections.observableArrayList();
            dishObservableArray.addAll(DishDictionary.dishes);
        }


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            dishList.setItems((ObservableList<Dish>) dishObservableArray);
            dishList.setCellFactory(studentListView -> new ListCellController());

            searchButton.setOnAction(actionEvent -> {
                String search = searchField.getText();
                if(search != ""){
                    try {
                        InteractionClient client = InteractionClient.getInstance();
                        Message messageToServer = new Message(1, search);
                        client.messageRequest(messageToServer);
                        Message messageFromServer = client.getMessage();
                        client.doCommand(messageFromServer);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }



}
