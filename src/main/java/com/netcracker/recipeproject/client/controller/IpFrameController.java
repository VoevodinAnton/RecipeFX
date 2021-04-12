package com.netcracker.recipeproject.client.controller;

import com.netcracker.recipeproject.client.model.InteractionClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class IpFrameController {

    @FXML
    private TextField ipField;

    @FXML
    private Button okeyButton;

    @FXML
    public void initialize(){
        okeyButton.setOnAction(actionEvent -> {
            String host;
            host = ipField.getText();
            InteractionClient client = InteractionClient.getInstance();
            client.setHost(host);
            String error = client.process();
            if(error.equals("")) {
                System.out.println("Подключен к серверу");

                Stage stageIp = (Stage) okeyButton.getScene().getWindow();
                stageIp.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/openFileFrame.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else {
                ipField.setText(error);
            }

        });
    }

}
