package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Store implements Storage {
    private static Store instance;


    public static synchronized Store getInstance() throws IOException {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }


    public ArrayList<Dish> getAllDishes() throws IOException {
        return DishDictionary.getInstance().getAllDishes();
    }

    public ArrayList<Ingredient> getAllIngredients() throws IOException {
        return IngredientDictionary.getInstance().getAllIngredients();
    }

    @Override
    public void addDish(Dish dish) throws IOException {
        if (!(DishDictionary.getInstance().getAllDishes().isEmpty())) {
            for (Dish thisDish : DishDictionary.getInstance().getAllDishes()) {
                if (thisDish.equals(dish)) {
                    throw new DuplicateFoundException();
                }
            }
        }
        dish.setId(this.lastIdOfLastDish() + 1);
        DishDictionary.getInstance().getAllDishes().add(dish);
    }

    @Override
    public void addIngredient(Ingredient ingredient) throws IOException {
        if (!IngredientDictionary.getInstance().getAllIngredients().isEmpty()) {
            for (Ingredient thisIngredient : IngredientDictionary.getInstance().getAllIngredients()) {
                if (thisIngredient.getName().equalsIgnoreCase(ingredient.getName())) {
                    throw new DuplicateFoundException();
                }
            }
        }
        ingredient.setId(this.lastIdOfLastIngredient() + 1);
        IngredientDictionary.getInstance().getAllIngredients().add(ingredient);
    }

    @Override
    public void removeDish(Dish dish) throws IOException {
        DishDictionary.getInstance().getAllDishes().remove(dish);
    }

    private void removeDish(Ingredient ingredient) throws IOException {
        ArrayList<Dish> dishes = new ArrayList<>(Store.getInstance().getAllDishes());
        for (Dish dish : dishes) {
            if (dish.contains(ingredient.getName())) {
                Store.getInstance().removeDish(dish);
            }
        }
    }

    @Override
    public void removeIngredient(Ingredient ingredient) throws IOException {
        IngredientDictionary.getInstance().getAllIngredients().remove(ingredient);
        removeDish(ingredient);
    }


    @Override
    public synchronized void editDish(Dish dish) throws IOException {
        int number = findDish(dish);
        DishDictionary.getInstance().getAllDishes().set(number, dish);

    }

    @Override
    public synchronized void editIngredient(Ingredient ingredient) throws IOException {
        int number = findIngredient(ingredient);
        IngredientDictionary.getInstance().getAllIngredients().set(number, ingredient);
        for (Dish thisDish : Store.instance.getAllDishes()) {
            if (thisDish.contains(ingredient.getId())) {
                int numberIngredient = thisDish.findIngredient(ingredient.getId());
                thisDish.getListOfIngredients().get(numberIngredient).setIngredient(ingredient);
            }
        }
    } //TODO: протестить все!!!!!!!!

    public int lastIdOfLastDish() throws IOException {
        if (DishDictionary.getInstance().getAllDishes().isEmpty()) {
            return 0;
        }
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getId);
        DishDictionary.getInstance().getAllDishes().sort(dishComparator);
        return DishDictionary.getInstance().getAllDishes().get(DishDictionary.getInstance().getAllDishes().size() - 1).getId();
    }

    public int lastIdOfLastIngredient() throws IOException {
        if (IngredientDictionary.getInstance().getAllIngredients().isEmpty()) {
            return 0;
        }
        Comparator<Ingredient> ingredientComparator = Comparator.comparingInt(Ingredient::getId);
        IngredientDictionary.getInstance().getAllIngredients().sort(ingredientComparator);
        return IngredientDictionary.getInstance().getAllIngredients().get(IngredientDictionary.getInstance().getAllIngredients().size() - 1).getId();
    }

    public int findDish(Dish dish) throws IOException {
        int i = 0;
        for (Dish thisDish : DishDictionary.getInstance().getAllDishes()) {
            if (thisDish.getId() == dish.getId()) {
                break;
            }
            i++;
        }
        return i;
    }


    public int findIngredient(Ingredient ingredient) throws IOException {
        int i = 0;
        for (Ingredient thisIngredient : IngredientDictionary.getInstance().getAllIngredients()) {

            if (thisIngredient.getId() == ingredient.getId()) {
                break;
            }
            i++;
        }
        return i;
    }

    private ArrayList<Dish> cloneDishes(ArrayList<Dish> dishes) {
        ArrayList<Dish> newDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            newDishes.add(dish);
        }
        return newDishes;
    }


}

