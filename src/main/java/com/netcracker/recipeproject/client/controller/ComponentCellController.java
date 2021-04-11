package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.netcracker.recipeproject.library.DishComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class ComponentCellController extends ListCell<DishComponent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label unitLabel;

    @FXML
    private AnchorPane pane;

    private FXMLLoader loader;

    @Override
    protected void updateItem(DishComponent component, boolean empty) {
        super.updateItem(component, empty);

        if (empty || component == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/com/netcracker/recipeproject/FXML/componentCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            nameLabel.setText(component.getIngredient().getName());
            numberLabel.setText(String.valueOf(component.getNumber()));
            unitLabel.setText(component.getIngredient().getUnit());


            setText(null);
            setGraphic(pane);
        }
    }

}
