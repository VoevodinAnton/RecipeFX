package com.netcracker.recipeproject.library;

import com.netcracker.recipeproject.server.model.DishDictionary;
import javafx.collections.ObservableArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Dish implements Serializable {
    private static final long serialVersionUID = 5863963783465074543L;
    private int id;
    private ArrayList<IngrWithNumber> listOfIngr;
    private String name;
    private String cookingTime;

    public Dish(ArrayList<IngrWithNumber> listOfIngr, String name, String cookingTime) {
        this.listOfIngr = listOfIngr;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public Dish(int id, ArrayList<IngrWithNumber> listOfIngr, String name, String cookingTime) {
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

    public ArrayList<String> nameOfIngredientsToArray() {
        ArrayList<String> listOfIngredient = new ArrayList();
        for (IngrWithNumber ingrWithNumber : listOfIngr) {
            listOfIngredient.add(ingrWithNumber.getIngredient().getName());
        }
        return listOfIngredient;
    }


    public boolean contains(String ingredientsSearch) {
        String[] listOfIngredients = ingredientsSearch.split(",\\s*"); //
        ArrayList<String> nameOfingredientsDish = this.nameOfIngredientsToArray();
        return nameOfingredientsDish.containsAll(Arrays.asList(listOfIngredients));
    }

}
