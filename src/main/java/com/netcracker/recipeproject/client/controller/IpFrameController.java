package com.netcracker.recipeproject.client.controller;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class IpFrameController {

    @FXML
    private TextField ipField;

    @FXML
    private Button okeyButton;

    @FXML
    void initialize(){
        okeyButton.setOnAction(actionEvent -> {
            String host = "";
            host = ipField.getText();
            int port = 2021;
            InteractionClient client = new InteractionClient(host, port);
            client.process();
            Message message = new Message(0, null);
            client.messageRequest(message);
        });
    }

}
