package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;

public class DishDictionaryTest {
    IngredientDictionary ingredientDictionary = new IngredientDictionary();
    DishDictionary dishDictionary = new DishDictionary();
    Ingredient egg = new Ingredient("яйцо", "шт");
    Ingredient sausage = new Ingredient("колбаса", "гр");
    DishComponent sausage1 = new DishComponent(sausage, 1);
    DishComponent egg3 = new DishComponent(egg, 3);
    ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
    Dish omelette = new Dish(ingredientsOfOmelette, "Омлет", "10");

    Ingredient eggDuplicate = new Ingredient("ЯйЦо", "шт");
    Ingredient sausageDuplicate = new Ingredient("молоко", "гр");
    DishComponent sausage1Duplicate = new DishComponent(sausageDuplicate, 1);
    DishComponent egg3Duplicate = new DishComponent(eggDuplicate, 3);
    ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
    Dish omeletteDuplicate = new Dish(ingredientsOfOmeletteDuplicate, "ОмлЕт", "10");


    @BeforeSuite
    public void setup() {
        ingredientDictionary.addIngredient(egg);
        ingredientDictionary.addIngredient(sausage);

        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);

        ingredientsOfOmeletteDuplicate.add(egg3Duplicate);
        ingredientsOfOmeletteDuplicate.add(sausage1Duplicate);

        dishDictionary.addDish(omelette);
        dishDictionary.addDish(omeletteDuplicate);
    }


    @Test
    public void testAddDish() {
        assertThrows(DuplicateFoundException.class, ()-> dishDictionary.addDish(omeletteDuplicate));
    }

    @Test
    public void testLastId() {
        assertEquals(dishDictionary.lastId(), dishDictionary.getDishes().size());
    }

    @Test
    public void testRemoveDish() {
        dishDictionary.removeDish(omelette);
        dishDictionary.removeDish(omeletteDuplicate);
        assertEquals(dishDictionary.getDishes().size(), 0);
    }
}