package com.netcracker.recipeproject.client.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.client.utils.Checks;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDishController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField timeField;

    @FXML
    private Button addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<DishComponent> listView;

    @FXML
    private ComboBox<String> ingredientsComboBox;

    @FXML
    private TextField numberField;

    @FXML
    private TextField unitField;

    @FXML
    private Button addIngredientButton;

    @FXML
    private Button deleteIngredientButton;

    ObservableList<DishComponent> observableList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        listView.setItems(observableList);
        listView.setCellFactory(dishComponentListView -> new ComponentCellController());

        List<Ingredient> ingredientArrayList = (List<Ingredient>) Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null).getObj();
        ArrayList<String> namesArray = new ArrayList<>();
        for(Ingredient ingredient : ingredientArrayList) {
            namesArray.add(ingredient.getName());
        }
        ingredientsComboBox.setItems(FXCollections.observableList(namesArray));
        StringBuffer ingredientNameBuffer = new StringBuffer();

        ingredientsComboBox.setOnAction(actionEvent -> {
            String nameIngredient = ingredientsComboBox.getValue();
            ingredientNameBuffer.append(nameIngredient);
            for(Ingredient ingredientItem : ingredientArrayList){
                if(ingredientItem.getName().equals(nameIngredient)){
                    unitField.setText(ingredientItem.getUnit());
                }
            }
        });

        addIngredientButton.setOnAction(actionEvent -> {
            String name = ingredientNameBuffer.toString();
            ingredientNameBuffer.setLength(0);
            if(Checks.checkingIngredient(numberField, name).equals(""))
            {
                int number = Integer.parseInt(numberField.getText());
                String unit = unitField.getText();
                observableList.add(new DishComponent(new Ingredient(name, unit), number));
            }
            errorLabel.setText(Checks.checkingIngredient(numberField, name));
        });
        DishComponent component = null;

        deleteIngredientButton.setOnAction(actionEvent -> {
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DishComponent>() {
                @Override
                public void changed(ObservableValue<? extends DishComponent> observableValue, DishComponent component, DishComponent t1) {
                    component = t1;
                }
            });
            observableList.remove(component);
        });

        addButton.setOnAction(actionEvent -> {
            String error = Checks.chekingDish(nameField, observableList, timeField);
            if(error.equals("")) {
                String name = nameField.getText();
                ArrayList<DishComponent> components = new ArrayList<>();
                components.addAll(observableList);
                String time = timeField.getText();
                Dish dish = new Dish(components, name, time);
                Message response = Messaging.execute(CommandEnum.ADD_A_DISH, dish);
                if (response.getFlag() == CommandEnum.OK) {
                    Stage stage = (Stage) addButton.getScene().getWindow();
                    stage.close();
                } else {
                    errorLabel.setText("Такое блюдо уже существует");
                }
            }
            errorLabel.setText(error);
        });

    }
}
