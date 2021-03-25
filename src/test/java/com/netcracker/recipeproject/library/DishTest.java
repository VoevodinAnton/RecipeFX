package com.netcracker.recipeproject.library;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class DishTest {
    Ingredient egg = new Ingredient("яйцо", "шт");
    Ingredient sausage = new Ingredient("колбаса", "гр");
    DishComponent sausage1 = new DishComponent(sausage, 1);
    DishComponent egg3 = new DishComponent(egg, 3);
    ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
    Dish omelette = new Dish(ingredientsOfOmelette, "omelette", "10");

    @BeforeSuite
    public void initialize(){
        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);

    }

    @Test
    public void testContains() {
        String search1 = "яйцо";
        String search2 = "яйцо, колбаса";

        assertTrue(omelette.contains(search1));
        assertTrue(omelette.contains(search2));
    }

    @Test
    public void testNameOfIngredientsToArray() {
        ArrayList<String> nameOfIngredients = new ArrayList();
        nameOfIngredients.add("колбаса");
        nameOfIngredients.add("яйцо");

        ArrayList<String> ingredientsOfDish = omelette.nameOfIngredientsToArray();


        assertEquals(ingredientsOfDish, nameOfIngredients);



    }
}