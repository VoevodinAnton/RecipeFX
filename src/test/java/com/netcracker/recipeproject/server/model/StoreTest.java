package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class StoreTest {

    @BeforeTest
    public void initializeDictionary() throws IOException {
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient chicken = new Ingredient("курица", "гр");
        DishComponent eggComponent = new DishComponent(egg, 4);
        DishComponent chickenComponent = new DishComponent(chicken, 1);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(eggComponent);
        ingredientsOfOmelette.add(chickenComponent);
        Dish omlette = new Dish(ingredientsOfOmelette, "Омлет", "10 минут");


        Ingredient rice = new Ingredient("рис", "гр");
        Ingredient meat = new Ingredient("мясо", "гр");
        DishComponent riceComponent = new DishComponent(rice, 4);
        DishComponent meatComponent = new DishComponent(meat, 1);
        ArrayList<DishComponent> ingredientsOfRwM = new ArrayList<>();
        ingredientsOfRwM.add(riceComponent);
        ingredientsOfRwM.add(meatComponent);
        Dish riceWithMeat = new Dish(ingredientsOfRwM, "Рис с мясом", "10 минут");

        Store.getInstance().addIngredient(egg);
        Store.getInstance().addIngredient(chicken);
        Store.getInstance().addDish(omlette);

        Store.getInstance().addIngredient(rice);
        Store.getInstance().addIngredient(meat);
        Store.getInstance().addDish(riceWithMeat);

    }


    @Test
    public void testAddDish() {
        try {
            assertEquals(Store.getInstance().getAllDishes().get(0).getName(), "Омлет");
            assertEquals(Store.getInstance().getAllDishes().get(1).getName(), "Рис с мясом");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAddDuplicateDish() {
        Ingredient eggDuplicate = new Ingredient("ЯйЦо", "шт");
        Ingredient chickenDuplicate = new Ingredient("КуриЦа", "гр");
        DishComponent chicken1Duplicate = new DishComponent(chickenDuplicate, 1);
        DishComponent egg3Duplicate = new DishComponent(eggDuplicate, 3);
        ArrayList<DishComponent> ingredientsOfOmeletteDuplicate = new ArrayList<>();
        ingredientsOfOmeletteDuplicate.add(chicken1Duplicate);
        ingredientsOfOmeletteDuplicate.add(egg3Duplicate);
        Dish omeletteDuplicate = new Dish(ingredientsOfOmeletteDuplicate, "ОмлЕт", "10 минут");

        assertThrows(DuplicateFoundException.class, () -> {
            Store.getInstance().addIngredient(eggDuplicate);
        });
        assertThrows(DuplicateFoundException.class, () -> {
            Store.getInstance().addIngredient(chickenDuplicate);
        });
        assertThrows(DuplicateFoundException.class, () -> {
            Store.getInstance().addDish(omeletteDuplicate);
        });
    }

    @Test
    public void testAddIngredient() {
        try {
            assertEquals(Store.getInstance().getAllIngredients().get(0).getName(), "яйцо");
            assertEquals(Store.getInstance().getAllIngredients().get(1).getName(), "курица");
            assertEquals(Store.getInstance().getAllIngredients().get(2).getName(), "рис");
            assertEquals(Store.getInstance().getAllIngredients().get(3).getName(), "мясо");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRemoveDish() {
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient chicken = new Ingredient("курица", "гр");
        DishComponent eggComponent = new DishComponent(egg, 4);
        DishComponent chickenComponent = new DishComponent(chicken, 1);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(eggComponent);
        ingredientsOfOmelette.add(chickenComponent);
        Dish omlette = new Dish(ingredientsOfOmelette, "Омлет", "10 минут");

        try {
            Store.getInstance().removeDish(omlette);
            assertEquals(Store.getInstance().getAllDishes().get(0).getName(), "Рис с мясом");
            assertEquals(Store.getInstance().lastIdOfLastDish(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRemoveIngredient() {
        Ingredient chicken = new Ingredient("курица", "гр");
        try {
            Store.getInstance().removeIngredient(chicken);
            assertEquals(Store.getInstance().getAllIngredients().get(0).getName(), "яйцо");
            assertEquals(Store.getInstance().getAllIngredients().get(1).getName(), "рис");
            assertEquals(Store.getInstance().getAllIngredients().get(2).getName(), "мясо");

            assertEquals(Store.getInstance().getAllDishes().get(0).getName(), "Рис с мясом");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEditDish() {
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient chicken = new Ingredient("курица", "гр");
        DishComponent eggComponent = new DishComponent(egg, 4);
        DishComponent chickenComponent = new DishComponent(chicken, 1);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(eggComponent);
        ingredientsOfOmelette.add(chickenComponent);
        Dish omelette = new Dish(ingredientsOfOmelette, "Яичница", "10 минут");
        omelette.setId(1);

        try {
            Store.getInstance().editDish(omelette);
            assertEquals(Store.getInstance().getAllDishes().get(0).getName(), "Яичница");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEditIngredient() {
        Ingredient egg = new Ingredient("яйцоо", "шт");
        egg.setId(1);
        try {
            //Store.getInstance().editIngredient(egg);
            //System.out.println(Store.getInstance().getAllDishes().get(1).getListOfIngredients().get(1).getIngredient().getId());
        } catch (Exception e){
            e.printStackTrace();
        }



        try {
            //System.out.println(Store.getInstance().findIngredient(egg));
            for (Ingredient ingredient: Store.getInstance().getAllIngredients()){
                System.out.println(ingredient.getId());
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void testIsExistIngredient() {
        try{
            assertTrue(Store.getInstance().isExistIngredient(Store.getInstance().getAllIngredients().get(0)));
        } catch (Exception e){

        }

    }
}