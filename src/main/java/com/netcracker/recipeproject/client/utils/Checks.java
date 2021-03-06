package com.netcracker.recipeproject.client.utils;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class Checks {

    public static String checkingComponent(TextField number, String name, ObservableList<DishComponent> list){
        String error = "";
        if(number.getText().equals(""))
            error += "Введите количество ингредиента\n";
        if(name == null || name.equals("") )
            error += "Выберите название ингредиента\n";
        for(DishComponent component : list){
            if(component.getIngredient().getName().equals(name))
                error += "Нельзя добавлять одинаковые ингредиенты";
        }
        return error;
    }

    public static String chekingDish(TextField name, ObservableList<DishComponent> components, TextField time){
        String error = "";
        if(name.getText().equals(""))
            error += "Введите название блюда\n";
        if(components.isEmpty())
            error += "Выберите ингредиенты\n";
        if (time.getText().equals(""))
            error += "Введите время приготовления\n";

        return error;
    }

    public static String checkingIngredient(TextField name, TextField unit){
        String error = "";
        if(name.getText().equals(""))
            error += "Введите название ингредиента\n";
        if(unit.getText().equals(""))
            error += "Введите единицу измерения\n";
        return error;
    }

}
