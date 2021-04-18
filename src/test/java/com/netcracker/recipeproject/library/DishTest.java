package com.netcracker.recipeproject.library;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class DishTest {

    private Dish getDishOmelette(){
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient sausage = new Ingredient("колбаса", "гр");
        DishComponent sausage1 = new DishComponent(sausage, 1);
        DishComponent egg3 = new DishComponent(egg, 3);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);
        return new Dish(ingredientsOfOmelette, "omelette", "10");
    }

    private Dish getDishOmeletteDuplicate(){
        Ingredient eggDuplicate = new Ingredient("ЯйЦо", "шт");
        Ingredient sausageDuplicate = new Ingredient("колбаса", "гр");
        DishComponent sausage1Duplicate = new DishComponent(sausageDuplicate, 1);
        DishComponent egg3Duplicate = new DishComponent(eggDuplicate, 3);
        ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
        ingredientsOfOmeletteDuplicate.add(egg3Duplicate);
        ingredientsOfOmeletteDuplicate.add(sausage1Duplicate);
        return new Dish(ingredientsOfOmeletteDuplicate, "omelette", "10");
    }

    private Dish getRiceWithMeat(){
        Ingredient rice = new Ingredient("рис", "гр");
        Ingredient meat = new Ingredient("мясо", "гр");
        DishComponent riceComponent = new DishComponent(rice, 4);
        DishComponent meatComponent = new DishComponent(meat, 1);
        ArrayList<DishComponent> ingredientsOfRwM = new ArrayList<>();
        ingredientsOfRwM.add(riceComponent);
        ingredientsOfRwM.add(meatComponent);
        return new Dish(ingredientsOfRwM, "Рис с мясом", "10 минут");
    }


    @DataProvider
    public Object[][] nameOfIngredientsToArrayData(){
        Dish omelette = getDishOmelette();

        ArrayList<String> nameOfIngredients = new ArrayList();
        nameOfIngredients.add("колбаса");
        nameOfIngredients.add("яйцо");

        ArrayList<String> ingredientsOfDish = omelette.nameOfIngredientsToArray();
        return new Object[][]{{null, null},{ingredientsOfDish, nameOfIngredients}};
    }

    @DataProvider
    public Object[][] containsData(){
        String search1 = "яйцо";
        String search2 = "яЙцо    , КоЛбаса";
        return new Object[][]{{search1}, {search2}};
    }

    @DataProvider
    public Object[][] equalsData(){
        Dish omelette = getDishOmelette();
        Dish omeletteDuplicate = getDishOmeletteDuplicate();
        return new Object[][]{ {omelette}, {omeletteDuplicate}};
    }

    @Test(dataProvider = "containsData")
    public void testContains(String searchString) {
        Dish omelette = getDishOmelette();
        System.out.println(searchString);
        assertTrue(omelette.contains(searchString));
    }

    @Test(dataProvider = "nameOfIngredientsToArrayData")
    public void testNameOfIngredientsToArray(ArrayList<String> actual, ArrayList<String> expected) {
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "equalsData")
    public void testEquals(Dish dish) {
        Dish omelette = getDishOmelette();
        assertTrue(omelette.equals(dish));
        assertTrue(omelette.equals(dish));
    }


}