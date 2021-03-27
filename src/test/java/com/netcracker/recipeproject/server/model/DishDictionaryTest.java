package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class DishDictionaryTest {
    IngredientDictionary ingredientDictionary = new IngredientDictionary();
    DishDictionary dishDictionary = new DishDictionary();
    Ingredient egg = new Ingredient("яйцо", "шт");
    Ingredient sausage = new Ingredient("колбаса", "гр");

    DishComponent sausage1 = new DishComponent(sausage, 1);
    DishComponent egg3 = new DishComponent(egg, 3);
    ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();

    Dish omelette = new Dish(ingredientsOfOmelette, "omelette", "10");


        @BeforeSuite
        public void setup(){
            ingredientDictionary.add(egg);
            ingredientDictionary.add(sausage);
            ingredientsOfOmelette.add(sausage1);
            ingredientsOfOmelette.add(egg3);
            dishDictionary.addDish(omelette);
        }

    @Test
    public void testAddDish() {
        System.out.println(omelette.getListOfIngr().toString());
        System.out.println(dishDictionary.getDishes().size());
    }
}