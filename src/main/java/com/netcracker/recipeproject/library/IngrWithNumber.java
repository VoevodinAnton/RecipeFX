package com.netcracker.recipeproject.library;

import java.io.Serializable;
import java.util.ArrayList;

import java.io.Serializable;

public class IngrWithNumber implements Serializable {
    private static final long serialVersionUID = -6912146482775838759L;
    private Ingredient ingredient;
    private int number;

    public IngrWithNumber(Ingredient ingredient, int number){
        this.ingredient = ingredient;
        this.number = number;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
