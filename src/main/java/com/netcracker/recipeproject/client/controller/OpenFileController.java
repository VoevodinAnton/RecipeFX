package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OpenFileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> fileNameComboBox;

    @FXML
    private TextField fileNameField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button okButton;

    @FXML
    void initialize() {

        Message response = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_FILENAMES, null);
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll((ArrayList<String>)response.getObj());
        fileNameComboBox.setItems(list);

        StringBuffer fileNameBuffer = new StringBuffer();
        fileNameComboBox.setOnAction(actionEvent -> {
            fileNameBuffer.append(fileNameComboBox.getValue());
        });

        okButton.setOnAction(actionEvent -> {
            String fileName = fileNameBuffer.toString();
            String fileNameNew = fileNameField.getText();
            if(fileNameComboBox.getValue() != null && !fileNameNew.equals(""))
                errorLabel.setText("Выберите что-то одно");
            else if (fileNameComboBox.getValue() != null) {
                errorLabel.setText("");
                Message response1 = Messaging.execute(CommandEnum.OPEN_A_FILE, fileNameComboBox.getValue());
                if(response1.getFlag() != CommandEnum.OK) {
                    errorLabel.setText("Не удалось открыть файл");
                    InteractionClient.getInstance().setFileName(fileNameComboBox.getValue());
                }
                else{
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
                }
            }
            else if (!fileNameNew.equals("")) {
                errorLabel.setText("");
                Message response1 = Messaging.execute(CommandEnum.OPEN_A_FILE, fileNameNew);
                if(response1.getFlag() != CommandEnum.OK) {
                    errorLabel.setText("Не удалось открыть файл");
                    InteractionClient.getInstance().setFileName(fileNameNew);
                }
                else{
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
                }
            }
            else{
                errorLabel.setText("Выберите действие");
            }

        });

    }
}
