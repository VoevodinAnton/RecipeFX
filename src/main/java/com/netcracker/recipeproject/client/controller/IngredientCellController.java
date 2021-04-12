package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngredientCellController extends ListCell<Ingredient> {


    @FXML
    private Label nameLabel;

    @FXML
    private Label unitLable;

    @FXML
    private ImageView editButton;

    @FXML
    private ImageView deleteButton;

    @FXML
    private AnchorPane pane;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Ingredient ingredient, boolean empty) {
        super.updateItem(ingredient, empty);

        if (empty || ingredient == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            nameLabel.setText(ingredient.getName());
            unitLable.setText(ingredient.getUnit());


            deleteButton.setOnMouseClicked(mouseEvent -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/deleteIngredient.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(this.deleteButton.getScene().getWindow());
                stage.showAndWait();
            });

            editButton.setOnMouseClicked(mouseEvent -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/editIngredient.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(this.deleteButton.getScene().getWindow());
                stage.showAndWait();
        });
        if(isSelected())
            setStyle("-fx-background-color: white");
        setText(null);
        setGraphic(pane);
        }
    }
}
