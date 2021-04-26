package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Checks;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditIngredientController {
    @FXML
    private static Ingredient ingredient = new Ingredient();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField unitField;

    @FXML
    private Button editButton;

    @FXML
    private Label errorLabel;

    public static void setIngredient(Ingredient ingredient1){
        ingredient = ingredient1;
    }

    @FXML
    void initialize() {
        nameField.setText(ingredient.getName());
        unitField.setText(ingredient.getUnit());

        editButton.setOnAction(actionEvent -> {
            String error = Checks.checkingIngredient(nameField, unitField);
            if(error.equals("")) {
                String name = nameField.getText();
                String unit = unitField.getText();
                Ingredient ingredient1 = new Ingredient(ingredient.getId(), name, unit);
                Message response = Messaging.execute(CommandEnum.EDIT_A_INGREDIENT, ingredient1);
                if (response.getFlag() == CommandEnum.OK) {
                    Stage stageIp = (Stage) editButton.getScene().getWindow();
                    stageIp.close();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientsFrame.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    IngredientsController controller = loader.getController();
                    Message message = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
                    controller.setIngredientObservableList((List<Ingredient>)message.getObj());

                    FXMLLoader loader2 = new FXMLLoader();
                    loader2.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
                    try {
                        loader2.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PrimaryController controller2 = loader2.getController();
                    Message message2 = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null);
                    controller2.setDishObservableList((List<Dish>)message2.getObj());

                } else if(response.getFlag() == CommandEnum.ADDING_A_DUPLICATE_INGREDIENT)
                    error += "Такой ингредиент уже существует";
                    else if(response.getFlag() == CommandEnum.NOT_OK)
                    error += "В данный момент редактирование ингредиента недоступно";
            }
            errorLabel.setText(error);
        });
    }
}
