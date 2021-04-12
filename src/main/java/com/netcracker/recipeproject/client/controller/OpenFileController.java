package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class OpenFileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> fileNameComboBox;

    @FXML
    private Button createButton;

    @FXML
    private Button okButton;

    @FXML
    void initialize() {

        //Message response = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_FILENAMES, null);
        //ObservableList<String> list = (ObservableList<String>)response.getObj();
        //fileNameComboBox.setItems(list);

        StringBuffer fileNameBuffer = new StringBuffer();
        fileNameComboBox.setOnAction(actionEvent -> {
            fileNameBuffer.append(fileNameComboBox.getValue());
        });

        okButton.setOnAction(actionEvent -> {
            String fileName = fileNameBuffer.toString();
            Messaging.execute(CommandEnum.OPEN_A_FILE, fileName);
            Stage stageIp = (Stage) okButton.getScene().getWindow();
            stageIp.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        createButton.setOnAction(actionEvent -> {
            Messaging.execute(CommandEnum.OPEN_A_FILE, "");
            Stage stageIp = (Stage) createButton.getScene().getWindow();
            stageIp.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}