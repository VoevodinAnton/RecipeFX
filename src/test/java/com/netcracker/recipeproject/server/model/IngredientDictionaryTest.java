package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Ingredient;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IngredientDictionaryTest {
    IngredientDictionary ingredientDictionary = new IngredientDictionary();
    Ingredient egg = new Ingredient("egg", "2");
    Ingredient milk = new Ingredient("milk", "300");
    Ingredient water = new Ingredient("water", "1000");
    Ingredient water1 = new Ingredient("water", "12");

    @Test
    public void testAdd() {
        ingredientDictionary.addIngredient(egg);
        ingredientDictionary.addIngredient(milk);
        ingredientDictionary.addIngredient(water);

        System.out.println(milk.getName());

    }

    @Test
    public void testLastId() {
        assertEquals(ingredientDictionary.lastId(), ingredientDictionary.getIngredients().size());
    }
}