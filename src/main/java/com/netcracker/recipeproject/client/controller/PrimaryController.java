package com.netcracker.recipeproject.client.controller;

import java.io.IOException;

import com.netcracker.recipeproject.client.view.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/com/netcracker/recipeproject/secondary.fxml");
        System.out.println("2");
    }
}
