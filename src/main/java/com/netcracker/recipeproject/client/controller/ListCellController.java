package com.netcracker.recipeproject.client.controller;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
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

import java.io.IOException;

public class ListCellController extends ListCell<Dish> {

    @FXML
    private Label nameDishLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label ingrLabel;

    @FXML
    private ImageView deleteButton;

    @FXML
    private ImageView editButton;

    @FXML
    private AnchorPane pane;

    private FXMLLoader loader;


    @Override
    protected void updateItem(Dish d, boolean empty){
        super.updateItem(d, empty);

        if(empty || d == null){
            setText(null);
            setGraphic(null);
        }
        else {
            if(loader == null){
                loader = new FXMLLoader(getClass().getResource("/com/netcracker/recipeproject/FXML/listcell.fxml"));
                loader.setController(this);
                try{
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            nameDishLabel.setText(d.getName());
            timeLabel.setText(d.getCookingTime());
            String ingrList = "";
            for(int i = 0; i < d.getListOfIngredients().size(); i++){
                DishComponent dishComponent = d.getListOfIngredients().get(i);
                ingrList += dishComponent.getIngredient().getName() + " " + dishComponent.getNumber() + " " + dishComponent.getIngredient().getUnit() + '\n';
            }
            ingrLabel.setText(ingrList);

            setText(null);
            setGraphic(pane);

            /*deleteButton.setOnMouseClicked(mouseEvent -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/deleteFrame.fxml"));
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
                loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/editDishFrame.fxml"));
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
            });*/
        }
    }

}

