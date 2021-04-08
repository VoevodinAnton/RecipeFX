package com.netcracker.recipeproject.library;

import jdk.jfr.DataAmount;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IngredientTest {

    @DataProvider
    public Object[][] equalsData() {
        return new Object[][]{
                {new Ingredient("яйцо", "шт"), new Ingredient("яйцо", "шт")},
                {new Ingredient("ЯЙЦО", "шт"), new Ingredient("яйцо", "шт")},
                {new Ingredient("колБаСа", "гр"), new Ingredient("колбаса", "гр")}

        };
    }


    @Test(dataProvider = "equalsData")
    public void testEquals(Ingredient actual, Ingredient expected) {
        assertEquals(actual, expected);
    }
}