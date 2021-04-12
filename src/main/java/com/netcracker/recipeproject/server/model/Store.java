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

    public void removeDish(Dish dish) throws IOException {
        DishDictionary.getInstance().getAllDishes().remove(dish);
    }

    public void removeDish(Ingredient ingredient) throws IOException {
        for (Dish dish : Store.getInstance().getAllDishes()) {
            if (dish.contains(ingredient.getName())) {
                Store.getInstance().removeDish(dish);
            }
        }
    }

    public void removeIngredient(Ingredient ingredient) throws IOException {
        IngredientDictionary.getInstance().getAllIngredients().remove(ingredient);
        removeDish(ingredient);
    } //TODO: сделать удаление блюд с этим ингредиентов


    public void editDish(Dish dish) throws IOException { //TODO: Разделить функцию поиска и функцию редактирования
        int number = findDish(dish);
        DishDictionary.getInstance().getAllDishes().set(number, dish);

    }

    public void editIngredient(Ingredient ingredient) throws IOException {
        int number = findIngredient(ingredient);
        IngredientDictionary.getInstance().getAllIngredients().set(number, ingredient);
    }

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
        for (Ingredient thisIngredient: IngredientDictionary.getInstance().getAllIngredients()){
            if (thisIngredient.getId() == ingredient.getId()){
                break;
            }
            i++;
        }
        return i;
    }
}

