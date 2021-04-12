package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IngredientDictionaryTest {
    /*
    private IngredientDictionary getIngredientDictionary(){
        IngredientDictionary ingredientDictionary = new IngredientDictionary();
        Ingredient cacao = new Ingredient("cacao", "300");
        Ingredient bread = new Ingredient("bread", "3");
        Ingredient lemon = new Ingredient("lemon", "2");
        ingredientDictionary.addIngredient(cacao);
        ingredientDictionary.addIngredient(bread);
        ingredientDictionary.addIngredient(lemon);
        return ingredientDictionary;
    }




    @DataProvider
    public Object[][] addIngredientData(){
        return new Object[][]{
                {new Ingredient("egg", "2")},
                {new Ingredient("milk", "300")},
                {new Ingredient("water", "1000")}
        };
    }

    @DataProvider
    public Object[][] noAddIngredientData(){
        return new Object[][]{
                {new Ingredient("bread", "10")},
        };
    }



    @Test(dataProvider = "addIngredientData")
    public void testAddIngredient(Ingredient ingredient) {
        IngredientDictionary ingredientDictionary = getIngredientDictionary();
        ingredientDictionary.addIngredient(ingredient);
    }

    @Test(dataProvider = "noAddIngredientData")
    public void testNoAddIngredientData(Ingredient ingredient){
        IngredientDictionary ingredientDictionary = getIngredientDictionary();
        assertThrows(DuplicateFoundException.class, ()-> ingredientDictionary.addIngredient(ingredient));
    }

    @Test
    public void testLastId() {
        IngredientDictionary ingredientDictionary = getIngredientDictionary();
        assertEquals(ingredientDictionary.lastId(), ingredientDictionary.getAllIngredients().size());
    }

    @Test
    public void testRemoveIngredient() {
        //out of list on the left
        IngredientDictionary ingredientDictionaryLeft = getIngredientDictionary();
        Ingredient cacao = new Ingredient("cacao", "2");
        ingredientDictionaryLeft.removeIngredient(cacao);
        assertEquals(ingredientDictionaryLeft.getAllIngredients().get(0).getName(), "bread");


        //inside the list
        IngredientDictionary ingredientDictionaryInside = getIngredientDictionary();
        Ingredient bread = new Ingredient("bread", "2");
        ingredientDictionaryInside.removeIngredient(bread);
        assertEquals(ingredientDictionaryInside.getAllIngredients().get(1).getName(), "lemon");

        //out of list on the right
        IngredientDictionary ingredientDictionaryRight = getIngredientDictionary();
        Ingredient lemon = new Ingredient("lemon", "2");
        ingredientDictionaryRight.removeIngredient(lemon);
        assertEquals(ingredientDictionaryRight.getAllIngredients().get(1).getName(), "bread");

    }


     */

}