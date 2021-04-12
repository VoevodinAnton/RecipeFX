/*
package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class DishDictionaryTest {



    @DataProvider
    public Object[][] addDishData(){
        return new Object[][]{
                {getOmeletteDuplicate()}
        };
    }



    @Test(dataProvider = "addDishData", enabled = false)
    public void testAddDish(Dish dish) {
        DishDictionary dishDictionary = getDishDictionary();
        assertThrows(DuplicateFoundException.class, ()-> dishDictionary.addDish(dish));
    }

    @Test(enabled = false)
    public void testLastId() {
        DishDictionary dishDictionary = getDishDictionary();
        assertEquals(dishDictionary.lastId(), dishDictionary.getAllDishes().size());
    }

    @Test(enabled = false)
    public void testRemoveDish() {
        DishDictionary dishDictionary = getDishDictionary();
        Dish omelette = getOmelette();
        dishDictionary.removeDish(omelette);
        assertEquals(dishDictionary.getAllDishes().get(0).getName(), "Паста с курицей");
    }


}

 */
