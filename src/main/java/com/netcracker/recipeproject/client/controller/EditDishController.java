package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDishController {

    private static Dish dish;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField timeField;

    @FXML
    private Button editButton;

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
        observableList.addAll(dish.getListOfIngredients());
        listView.setItems(observableList);
        listView.setCellFactory(dishComponentListView -> new ComponentCellController());
        nameField.setText(dish.getName());
        timeField.setText(dish.getCookingTime());

        List<Ingredient> ingredientArrayList = (List<Ingredient>) Messaging.execute(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, null).getObj();
        ArrayList<String> namesArray = new ArrayList<>();
        for(Ingredient ingredient : ingredientArrayList) {
            namesArray.add(ingredient.getName());
        }
        ingredientsComboBox.setItems(FXCollections.observableList(namesArray));
        StringBuffer ingredientNameBuffer = new StringBuffer();

        ingredientsComboBox.setOnAction(actionEvent -> {
            String nameIngredient = ingredientsComboBox.getValue();
            ingredientNameBuffer.setLength(0);
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
                    String error = Checks.checkingComponent(numberField, ingredientsComboBox.getValue(), observableList);
                    if (error.equals("")) {
                        int number = Integer.parseInt(numberField.getText());
                        String unit = unitField.getText();
                        observableList.add(new DishComponent(new Ingredient(name, unit), number));
                    }
                    errorLabel.setText(error);
                });

        deleteIngredientButton.setOnAction(actionEvent -> {
            DishComponent component1 = this.listView.getSelectionModel().getSelectedItem();
            listView.getItems().remove(component1);
        });

        editButton.setOnAction(actionEvent -> {
            String error = Checks.chekingDish(nameField, observableList, timeField);
            if(error.equals("")) {
                String name = nameField.getText();
                ArrayList<DishComponent> components = new ArrayList<>();
                components.addAll(observableList);
                String time = timeField.getText();
                int id = dish.getId();
                dish = new Dish(id, components, name, time);
                Message response = Messaging.execute(CommandEnum.EDIT_A_DISH, dish);
                if (response.getFlag() == CommandEnum.OK) {
                    Stage stage = (Stage) editButton.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/primary.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PrimaryController controller = loader.getController();

                    Message message = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null);
                    controller.setDishObservableList((List<Dish>)message.getObj());
                }
                else if(response.getFlag() == CommandEnum.ADDING_A_DUPLICATE_DISH)
                    error += "Такое блюдо уже существует";
                else if(response.getFlag() == CommandEnum.NOT_OK)
                    error += "В данный момент редактирование блюда недоступно";
            }
            errorLabel.setText(error);
        });

    }

    public static void setDish(Dish dish1){
        dish = dish1;
    }
}
