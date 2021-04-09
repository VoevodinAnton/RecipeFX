package com.netcracker.recipeproject.client.controller;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.library.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditDishController {

    private static Dish dish;

    public static void setDish(Dish dish1) {
        dish = dish1;
    }

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
        private Button editButton;

        @FXML
        private Label errorLabel;

        @FXML
        void initialize() {
            try {
                //TODO: сделать вывод изменяемого блюда
                nameField.setText(this.dish.getName());
                timeField.setText(this.dish.getCookingTime());
                //вывести ингредиенты
                InteractionClient client = InteractionClient.getInstance();
                Message messageOut = new Message(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null);
                client.messageRequest(messageOut);
                Message messageIn = client.getMessage();
                ArrayList<Ingredient> ingredientArrayList = (ArrayList<Ingredient>)messageIn.getObj();
                ArrayList<String> namesArray = new ArrayList<>();
                for(Ingredient ingredient : ingredientArrayList) {
                    namesArray.add(ingredient.getName());
                }
                IngredientComboBox.setItems(FXCollections.observableList(namesArray));
                StringBuffer ingredientNameBuffer = new StringBuffer();
                IngredientComboBox.setOnAction(actionEvent -> {
                    String nameOfIngredient1 = IngredientComboBox.getValue();
                    ingredientNameBuffer.append(nameOfIngredient1);
                    String nameIngredient = ingredientNameBuffer.toString();
                    for(Ingredient ingredientItem : ingredientArrayList){
                        if(ingredientItem.getName().equals(nameIngredient)){
                            unitField.setText(ingredientItem.getUnit());
                        }
                    }

                });

                //TODO:сделать добавление нескольких ингредиентов
                editButton.setOnAction(actionEvent -> {
                    if(!nameField.getText().equals("") && !numberField.getText().equals("") && !timeField.getText().equals("")) {
                        errorLabel.setText("");
                        String name = nameField.getText();
                        String time = timeField.getText();
                        Ingredient ingredient = null;
                        for(Ingredient ingredientItem : ingredientArrayList){
                            if(ingredientItem.getName().equals(IngredientComboBox.getValue())){
                                ingredient = ingredientItem;
                            }
                        }
                        DishComponent dishComponent = new DishComponent(ingredient, Integer.parseInt(numberField.getText()));
                        ArrayList<DishComponent> dishComponentArrayList = new ArrayList<DishComponent>();
                        dishComponentArrayList.add(dishComponent);
                        Dish dish = new Dish(this.dish.getId(), dishComponentArrayList, name, time);
                        Message messageToServer = new Message(CommandEnum.EDIT_A_DISH, dish);
                        try {
                            client.messageRequest(messageToServer);
                            Message messageFromServer = client.getMessage();
                            if(messageFromServer.getFlag() == CommandEnum.OK) {
                                Stage stageAdd = (Stage) editButton.getScene().getWindow();
                                stageAdd.close();
                            }
                            else
                                errorLabel.setText("Такое блюдо уже существует");

                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        errorLabel.setText("Заполнены не все поля");
                    }
                });
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

