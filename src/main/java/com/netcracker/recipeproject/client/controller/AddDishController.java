package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddDishController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addIngredientButton;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> IngredientComboBox;

    @FXML
    private TextField numberField;

    @FXML
    private TextField unitField;

    @FXML
    private ImageView cancelButton;

    @FXML
    private TextField timeField;

    @FXML
    private Button addButton;

    @FXML
    private Label errorLabel;

    @FXML
    void initialize() {
        try {
            InteractionClient client = InteractionClient.getInstance();
            Message messageOut = new Message(8, null);
            client.messageRequest(messageOut);
            Message messageIn = client.getMessage();
            ArrayList<Ingredient> ingredientArrayList = (ArrayList<Ingredient>)messageIn.getObj();
            ArrayList<String> namesArray = new ArrayList<>();
            for(Ingredient ingredient : ingredientArrayList) {
                namesArray.add(ingredient.getName());
            }
                IngredientComboBox = new ComboBox<>(FXCollections.observableList(namesArray));
                StringBuffer ingredientNameBuffer = new StringBuffer();
                IngredientComboBox.setOnAction(actionEvent -> {
                    ingredientNameBuffer.append(IngredientComboBox.getValue());
                });
                String nameIngredient = ingredientNameBuffer.toString();
                Ingredient ingredient = null;
                for(Ingredient ingredientItem : ingredientArrayList){
                    if(ingredientItem.getName().equals(nameIngredient)){
                        ingredient = ingredientItem;
                    }
                }
                addButton.setOnAction(actionEvent -> {
                    if(nameField.getText().equals("")) {
                        errorLabel.setText("");
                        String name = nameField.getText();
                        if(numberField.getText().equals("")){
                            errorLabel.setText("");
                            //int number = (int)numberField.getText();
                        }
                    }
                    else{
                        errorLabel.setText("Введите название");
                    }
                });
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
