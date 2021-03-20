package com.netcracker.recipeproject.client.controller;

import java.io.IOException;

import com.netcracker.recipeproject.client.view.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

        @FXML
        private Button searchButton;

        @FXML
        private Button allDishButton;

        @FXML
        private TextField searchField;

        @FXML
        private ListView<?> dishList;

        @FXML
        private Button sortButton;

        @FXML
        private Button allDishButton2;

        @FXML
        private Button allDishButton21;


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/com/netcracker/recipeproject/FXML/secondary.fxml");
        System.out.println("2");
    }

    @FXML
    private void search(){
        System.out.println("search");
    }
}
