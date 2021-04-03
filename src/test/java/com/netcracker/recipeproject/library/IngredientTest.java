package com.netcracker.recipeproject.library;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IngredientTest {
    Ingredient egg = new Ingredient("яйцо", "шт");
    Ingredient sausage = new Ingredient("колбаса", "гр");

    Ingredient eggDuplicate = new Ingredient("ЯЙЦО", "шт");
    Ingredient sausageDuplicate = new Ingredient("колбасаа", "гр");

    @Test
    public void testTestEquals() {
        assertTrue(egg.equals(egg));
        assertTrue(egg.equals(eggDuplicate));
        assertFalse(sausage.equals(sausageDuplicate));
    }
}