package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;
/*
public class StoreTest {

    private Dish getOmelette(){
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient sausage = new Ingredient("колбаса", "гр");
        DishComponent sausage1 = new DishComponent(sausage, 1);
        DishComponent egg3 = new DishComponent(egg, 3);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);
        return new Dish(ingredientsOfOmelette, "Омлет", "10");
    }

    private Dish getPaste(){
        Ingredient ingredient1 = new Ingredient("Спаггети", "шт");
        Ingredient ingredient2 = new Ingredient("курица", "гр");
        DishComponent dishComponent1 = new DishComponent(ingredient1, 1);
        DishComponent dishComponent2 = new DishComponent(ingredient2, 3);
        ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
        ingredientsOfOmeletteDuplicate.add(dishComponent1);
        ingredientsOfOmeletteDuplicate.add(dishComponent2);
        return new Dish(ingredientsOfOmeletteDuplicate, "Паста с курицей", "20 минут");
    }

    private Dish getOmeletteDuplicate(){
        Ingredient eggDuplicate = new Ingredient("ЯйЦо", "шт");
        Ingredient sausageDuplicate = new Ingredient("коЛбаса", "гр");
        DishComponent sausage1Duplicate = new DishComponent(sausageDuplicate, 1);
        DishComponent egg3Duplicate = new DishComponent(eggDuplicate, 3);
        ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
        ingredientsOfOmeletteDuplicate.add(sausage1Duplicate);
        ingredientsOfOmeletteDuplicate.add(egg3Duplicate);
        return new Dish(ingredientsOfOmeletteDuplicate, "ОмлЕт", "10");
    }

    private DishDictionary getDishDictionary(){
        DishDictionary dishDictionary = new DishDictionary();
        Dish omelette = getOmelette();
        Dish paste = getPaste();

        dishDictionary.addDish(omelette);
        dishDictionary.addDish(paste);

        return dishDictionary;
    }

    @Test
    public void testAddDish() {
    }

    @Test
    public void testAddIngredient() {
    }

    @Test
    public void testRemoveDish() {
    }

    @Test
    public void testTestRemoveDish() {
    }

    @Test
    public void testRemoveIngredient() {
    }

    @Test
    public void testEditDish() {
    }

    @Test
    public void testEditIngredient() {
    }

    @Test
    public void testLastIdOfLastDish() {
    }

    @Test
    public void testLastIdOfLastIngredient() {
    }

    @Test
    public void testFindDish() {
    }

    @Test
    public void testFindIngredient() {
    }
}

 */