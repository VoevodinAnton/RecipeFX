package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;

public class DishDictionaryTest {

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

    private Dish getPaste(){
        Ingredient ingredient1 = new Ingredient("Спаггети", "шт");
        Ingredient ingredient2 = new Ingredient("курица", "гр");
        DishComponent dishComponent1 = new DishComponent(ingredient1, 1);
        DishComponent dishComponent2 = new DishComponent(ingredient2, 3);
        ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
        ingredientsOfOmeletteDuplicate.add(dishComponent1);
        ingredientsOfOmeletteDuplicate.add(dishComponent2);
        return new Dish(ingredientsOfOmeletteDuplicate, "Паста с курицей", "20");
    }



    private DishDictionary getDishDictionary(){
        DishDictionary dishDictionary = new DishDictionary();
        Dish omelette = getOmelette();
        Dish paste = getPaste();

        dishDictionary.addDish(omelette);
        dishDictionary.addDish(paste);

        return dishDictionary;
    }


    @DataProvider
    public Object[][] addDishData(){
        return new Object[][]{
                {getOmeletteDuplicate()}
        };
    }



    @Test(dataProvider = "addDishData")
    public void testAddDish(Dish dish) {
        DishDictionary dishDictionary = getDishDictionary();
        assertThrows(DuplicateFoundException.class, ()-> dishDictionary.addDish(dish));
    }

    @Test
    public void testLastId() {
        DishDictionary dishDictionary = getDishDictionary();
        assertEquals(dishDictionary.lastId(), dishDictionary.getAllDishes().size());
    }

    @Test
    public void testRemoveDish() {
        DishDictionary dishDictionary = getDishDictionary();
        Dish omelette = getOmelette();
        dishDictionary.removeDish(omelette);
        assertEquals(dishDictionary.getAllDishes().get(0).getName(), "Паста с курицей");
    }
}