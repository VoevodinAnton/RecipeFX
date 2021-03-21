package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.DishDictionary;
import com.netcracker.recipeproject.client.view.App;
import com.netcracker.recipeproject.library.Dish;
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

        private ObservableArray<Dish> dishObservableArray;

        public PrimaryController(){
            dishObservableArray = (ObservableArray<Dish>) DishDictionary.dishes;
        }


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            dishList.setItems((ObservableList<Dish>) dishObservableArray);
            dishList.setCellFactory(studentListView -> new ListCellController());

        }
        @FXML
        private void switchToSecondary() throws IOException {
            App.setRoot("/com/netcracker/recipeproject/FXML/secondary.fxml");
            System.out.println("2");
        }


}
