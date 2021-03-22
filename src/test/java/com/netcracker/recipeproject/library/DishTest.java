package com.netcracker.recipeproject.library;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class DishTest {
    Ingredient egg = new Ingredient("яйцо", "шт");
    Ingredient sausage = new Ingredient("колбаса", "шт");
    IngrWithNumber sausage1 = new IngrWithNumber(sausage, 1);
    IngrWithNumber egg3 = new IngrWithNumber(egg, 3);
    ArrayList<IngrWithNumber> ingredientsOfOmelette = new ArrayList<>();


    @Test
    public void testContains() {
        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);
        Dish omelette = new Dish(ingredientsOfOmelette, "omelette", "10");
        String[] search1 = new String[]{"яйцо"};
        String[] search2 = new String[]{"яйцо"};

        assertTrue(omelette.contains(search1));
        assertTrue(omelette.contains(search2));

    }
}