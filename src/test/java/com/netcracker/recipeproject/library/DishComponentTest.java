package com.netcracker.recipeproject.library;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DishComponentTest {
    Ingredient egg = new Ingredient("яйцо", "шт");
    Ingredient sausage = new Ingredient("колбаса", "гр");
    Ingredient eggDuplicate = new Ingredient("яйцооо", "шт");
    Ingredient sausageDuplicate = new Ingredient("колбаса", "гр");

    DishComponent sausage1 = new DishComponent(sausage, 1);
    DishComponent egg3 = new DishComponent(egg, 3);
    DishComponent sausage1Duplicate = new DishComponent(sausageDuplicate, 1);
    DishComponent egg3Duplicate = new DishComponent(eggDuplicate, 3);

    @Test
    public void testTestEquals() {
        assertTrue(sausage1.equals(sausage1));
        assertTrue(sausage1.equals(sausage1Duplicate));
        assertTrue(egg3.equals(egg3));
        assertFalse(egg3.equals(egg3Duplicate));
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, sausage1.compareTo(sausage1Duplicate));
        assertEquals(21, egg3.compareTo(sausage1));
        assertEquals(-21, sausage1.compareTo(egg3));

    }
}