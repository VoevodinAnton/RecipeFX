package com.netcracker.recipeproject.library;

public class IngrWithNumber {
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