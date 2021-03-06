package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.*;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.Exceptions.IngredientDoesNotExistException;

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
    public synchronized void addDish(Dish dish) throws IOException, DuplicateFoundException, IngredientDoesNotExistException {
        if (!(DishDictionary.getInstance().getAllDishes().isEmpty())) {
            for (Dish thisDish : DishDictionary.getInstance().getAllDishes()) {
                if (thisDish.equals(dish)) {
                    throw new DuplicateFoundException();
                }
            }
        }
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (DishComponent dishComponent : dish.getListOfIngredients()) {
            ingredients.add(dishComponent.getIngredient());
        }
        if (!Store.getInstance().getAllIngredients().containsAll(ingredients)) {
            throw new IngredientDoesNotExistException();
        }
        dish.setId(this.lastIdOfLastDish() + 1);
        DishDictionary.getInstance().getAllDishes().add(dish);
    }

    @Override
    public synchronized void addIngredient(Ingredient ingredient) throws IOException, DuplicateFoundException {
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
    public synchronized void removeDish(Dish dish) throws IOException {
        DishDictionary.getInstance().getAllDishes().remove(dish);
    }

    private synchronized void removeDish(Ingredient ingredient) throws IOException {
        ArrayList<Dish> dishes = new ArrayList<>(Store.getInstance().getAllDishes());
        for (Dish dish : dishes) {
            if (dish.contains(ingredient.getName())) {
                Store.getInstance().removeDish(dish);
            }
        }
    }

    @Override
    public synchronized void removeIngredient(Ingredient ingredient) throws IOException {
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
    } //TODO: ???????????????????? ??????!!!!!!!!

    private int lastIdOfLastDish() throws IOException {
        if (DishDictionary.getInstance().getAllDishes().isEmpty()) {
            return 0;
        }
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getId);
        DishDictionary.getInstance().getAllDishes().sort(dishComparator);
        return DishDictionary.getInstance().getAllDishes().get(DishDictionary.getInstance().getAllDishes().size() - 1).getId();
    }

    private int lastIdOfLastIngredient() throws IOException {
        if (IngredientDictionary.getInstance().getAllIngredients().isEmpty()) {
            return 0;
        }
        Comparator<Ingredient> ingredientComparator = Comparator.comparingInt(Ingredient::getId);
        IngredientDictionary.getInstance().getAllIngredients().sort(ingredientComparator);
        return IngredientDictionary.getInstance().getAllIngredients().get(IngredientDictionary.getInstance().getAllIngredients().size() - 1).getId();
    }

    private int findDish(Dish dish) throws IOException {
        int i = 0;
        for (Dish thisDish : DishDictionary.getInstance().getAllDishes()) {
            if (thisDish.getId() == dish.getId()) {
                break;
            }
            i++;
        }
        return i;
    }


    private int findIngredient(Ingredient ingredient) throws IOException {
        int i = 0;
        for (Ingredient thisIngredient : IngredientDictionary.getInstance().getAllIngredients()) {

            if (thisIngredient.getId() == ingredient.getId()) {
                break;
            }
            i++;
        }
        return i;
    }


    public boolean isExistIngredient(Ingredient ingredient) throws IOException {
        for (Ingredient thisIngredient : Store.getInstance().getAllIngredients()) {
            if (thisIngredient.getId() == ingredient.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistDish(Dish dish) throws IOException {
        for (Dish thisDish : Store.instance.getAllDishes()) {
            if (thisDish.getId() == dish.getId()) {
                return true;
            }
        }
        return false;
    }
}

