package com.netcracker.recipeproject.library;

import com.netcracker.recipeproject.server.model.DishDictionary;
import javafx.collections.ObservableArray;

import java.util.ArrayList;

public class Dish implements ObservableArray<Dish> {
    private int id;
    private ArrayList<IngrWithNumber> listOfIngr;
    private String name;
    private String cookingTime;

    public Dish(ArrayList<IngrWithNumber> listOfIngr, String name, String cookingTime){
        this.listOfIngr = listOfIngr;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public Dish(int id, ArrayList<IngrWithNumber> listOfIngr, String name, String cookingTime){
        this.id = id;
        this.listOfIngr = listOfIngr;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<IngrWithNumber> getListOfIngr() {
        return listOfIngr;
    }

    public void setListOfIngr(ArrayList<IngrWithNumber> listOfIngr) {
        this.listOfIngr = listOfIngr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public boolean contains(String[] ingredients){

        for (IngrWithNumber ingr : listOfIngr){
            for (int i = 0; i < ingredients.length; i++){

            }
        }
        //TODO: create contains
         return true;
    }
}
