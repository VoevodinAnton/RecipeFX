package com.netcracker.recipeproject.client.controller;

import java.io.IOException;

import com.netcracker.recipeproject.client.view.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("/com/netcracker/recipeproject/FXML/primary.fxml");
        System.out.println("1");
    }
}