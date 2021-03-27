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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
            dishList.setItems(FXCollections.observableArrayList(DishDictionary.dishes));
            dishList.setCellFactory(studentListView -> new ListCellController());

            searchButton.setOnAction(actionEvent -> {
                String search = searchField.getText();
                if(search != ""){
                    System.out.println("*"+ search);
                    try {
                        InteractionClient client = InteractionClient.getInstance();
                        Message messageToServer = new Message(0, search);
                        client.messageRequest(messageToServer);
                        Message messageFromServer = client.getMessage();
                        client.doCommand(messageFromServer);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            ingredientsButton.setOnAction(actionEvent -> {
                try {
                    InteractionClient client = InteractionClient.getInstance();
                    Message messageToServer = new Message(8, null);
                    client.messageRequest(messageToServer);
                    Message messageFromServer = client.getMessage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientsFrame.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
}



