package com.netcracker.recipeproject.library;

import java.io.Serializable;
import java.util.ArrayList;

import java.io.Serializable;
import java.util.Objects;

public class DishComponent implements Serializable, Comparable<DishComponent> {
    private static final long serialVersionUID = -6912146482775838759L;
    private Ingredient ingredient;
    private int number;

    public DishComponent() {
        this.ingredient = new Ingredient();
        this.number = 0;
    }
    public DishComponent(Ingredient ingredient, int number) {
        this.ingredient = ingredient;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishComponent that = (DishComponent) o;
        return ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient);
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

    @Override
    public int compareTo(DishComponent o) {
        Ingredient thisIngredient = this.getIngredient();
        Ingredient anotherIngredient = o.getIngredient();

        return thisIngredient.compareTo(anotherIngredient);
    }
}
