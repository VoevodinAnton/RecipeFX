package com.netcracker.recipeproject.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Dish implements Serializable, Comparable<Dish> {
    private static final long serialVersionUID = 5863963783465074543L;
    private int id;
    private ArrayList<DishComponent> listOfIngr;
    private String name;
    private String cookingTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Dish(ArrayList<DishComponent> listOfIngr, String name, String cookingTime) {
        this.listOfIngr = listOfIngr;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public Dish(int id, ArrayList<DishComponent> listOfIngr, String name, String cookingTime) {
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

    public ArrayList<DishComponent> getListOfIngr() {
        return listOfIngr;
    }

    public void setListOfIngr(ArrayList<DishComponent> listOfIngr) {
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
        for (DishComponent dishComponent : listOfIngr) {
            listOfIngredient.add(dishComponent.getIngredient().getName());
        }
        return listOfIngredient;
    }


    public boolean contains(String ingredientsSearch) {
        String[] listOfIngredients = ingredientsSearch.split("\\s*,\\s*"); //
        ArrayList<String> nameOfIngredientsDish = this.nameOfIngredientsToArray();
        return nameOfIngredientsDish.containsAll(Arrays.asList(listOfIngredients));
    }

    @Override
    public int compareTo(Dish dish) {
        Integer thisTime = Integer.parseInt(cookingTime);
        Integer anotherTime = Integer.parseInt(dish.getCookingTime());
        return thisTime.compareTo(anotherTime);
    }
}
