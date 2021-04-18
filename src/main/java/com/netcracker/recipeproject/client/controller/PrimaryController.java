package com.netcracker.recipeproject.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.recipeproject.client.model.InteractionClient;
import com.netcracker.recipeproject.client.utils.Messaging;
import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComparator;
import com.netcracker.recipeproject.library.Message;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController{

        @FXML
        private Button searchButton;

        @FXML
        private Button allDishButton;

        @FXML
        private TextField searchField;

        @FXML
        private ListView<Dish> dishList;

        @FXML
        private Button sortButton;

        @FXML
        private Button addDishButton;

        @FXML
        private Button ingredientsButton;

        @FXML
        private Label errorLabel;

        @FXML
        private Button saveButton;

        private static ObservableList<Dish> dishObservableList = FXCollections.observableArrayList();

        int flag = 0;

        public void setDishObservableList(List<Dish> list){
            if(!list.isEmpty()){
                errorLabel.setText("");
                dishList.setVisible(true);
            }
            else{
                dishList.setVisible(false);
                errorLabel.setText("Сейчас в нашей базе данных нет блюд! \nВы можете добавить свое!");
            }

            dishList.getItems().clear();
            dishObservableList.removeAll();
            dishObservableList.addAll(list);
        }

        @FXML
        public void initialize(){
                Object obj = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null).getObj();
                setDishObservableList((List<Dish>)obj);
                dishList.setItems(dishObservableList);
                dishList.setCellFactory(dishListView -> new ListCellController());

            dishList.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<Dish>() {
                        @Override
                        public void changed(ObservableValue<? extends Dish> observableValue, Dish dish, Dish t1) {
                            DeleteDishController.setDish(t1);
                            EditDishController.setDish(t1);
                        }
            });
            searchButton.setOnAction(actionEvent -> {
                String search = searchField.getText();
                if(!search.equals("")){
                        Object object = Messaging.execute(CommandEnum.SEARCH, search).getObj();
                        dishList.getItems().clear();
                        if(!((List<Dish>)object).isEmpty()){
                            setDishObservableList((List<Dish>)object);
                            dishList.setItems(dishObservableList);
                            dishList.setCellFactory(dishListView -> new ListCellController());
                        }
                        else{
                            dishList.setVisible(false);
                            errorLabel.setText("По вашему запросу ничего не найдено");
                        }
                }
            });

            ObservableList<Dish> list = FXCollections.observableArrayList();
            list.addAll(dishObservableList);
            sortButton.setOnAction(actionEvent -> {
                switch (flag){
                    case 0:
                        DishComparator comparator = new DishComparator();
                        Collections.sort(dishObservableList, comparator);
                        flag = 1;
                        break;
                    case 1:
                        dishObservableList = list;
                        flag = 0;
                        break;
                }
            });
            ingredientsButton.setOnAction(actionEvent -> {
                try {
                    Stage stageP = (Stage) ingredientsButton.getScene().getWindow();
                    stageP.close();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/ingredientsFrame.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            addDishButton.setOnAction(actionEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/netcracker/recipeproject/FXML/addDishFrame.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(this.addDishButton.getScene().getWindow());
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            allDishButton.setOnAction(actionEvent -> {
                    Object object = Messaging.execute(CommandEnum.OUTPUT_OF_ALL_DISHES, null).getObj();
                    setDishObservableList((List<Dish>)object);
            });

            saveButton.setOnAction(actionEvent -> {
                String fileName = InteractionClient.getInstance().getFileName();
                Message response = Messaging.execute(CommandEnum.UPLOAD_TO_FILE, fileName);
                if(response.getFlag() != CommandEnum.OK){
                    dishList.setVisible(false);
                    errorLabel.setText("Не удалось загрузить в файл");
                }
            });

        }
}



