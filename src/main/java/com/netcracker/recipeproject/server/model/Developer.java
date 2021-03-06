package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.Exceptions.IngredientDoesNotExistException;
import com.netcracker.recipeproject.server.IO.RecipeIO;
import com.netcracker.recipeproject.server.model.DishDictionary;
import com.netcracker.recipeproject.server.model.IngredientDictionary;
import com.netcracker.recipeproject.server.model.Store;
import com.netcracker.recipeproject.server.model.utils.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Developer {
    public Message doCommand(Message message) throws IOException {
        CommandEnum flag = message.getFlag();
        switch (flag) {
            case SEARCH: //Search
                return searchDish(message);
            case OUTPUT_OF_ALL_DISHES: //output of all dishes
                return dishesOutput();
            case EDIT_A_DISH: //edit a dish
                return editDish(message);
            case ADD_A_DISH: //add a dish
                return addDish(message);
            case REMOVE_A_DISH: //remove a dish
                return removeDish(message);
            case REMOVE_A_INGREDIENT: //remove a ingredient
                return removeIngredient(message);
            case ADD_AN_INGREDIENT: //add an ingredient
                return addIngredient(message);
            case EDIT_A_INGREDIENT: // edit a ingredient
                return editIngredient(message);
            case OUTPUT_OF_ALL_INGREDIENTS: //output of all ingredients
                return ingredientsOutput();
            case UPLOAD_TO_FILE:
                return uploadToFile(message);
            case UPLOAD_FROM_FILE:
                return uploadFromFile(message);
            default:
                return null;
        }
    }

    private Message searchDish(Message message) throws IOException {
        Object object = message.getObj();
        String searchString = (String) object;

        ArrayList<Dish> dishesToClient = new ArrayList<>(); //???????????? ????????, ?????????????? ???????????????????????? ??????????????

        for (Dish dish : DishDictionary.getInstance().getAllDishes()) {
            if (dish.contains(searchString)) {
                dishesToClient.add(dish);
            }
        }
        return new Message(CommandEnum.SEARCH, dishesToClient);
    }

    private Message dishesOutput() throws IOException {
        int i = 0;
        ArrayList<Dish> dishes = new ArrayList<>();
        for (Dish dish : Store.getInstance().getAllDishes()) {
            System.out.println("?????????? " + ++i + ": " + dish.getName());
            for (int j = 0; j < dish.getListOfIngredients().size(); j++) {
                System.out.println("?????????????????????? ?????????? " + i + "(name): " + dish.getListOfIngredients().get(j).getIngredient().getName());
            }
            dishes.add(dish);
        }
        return new Message(CommandEnum.OUTPUT_OF_ALL_DISHES, dishes);
    }

    private Message ingredientsOutput() throws IOException {
        int j = 0;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : Store.getInstance().getAllIngredients()) {
            System.out.println("???????????????????? " + ++j + ": " + ingredient.getName());
            ingredients.add(ingredient);
        }
        return new Message(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, ingredients);
    }

    private Message addDish(Message message) {
        Object object = message.getObj();
        Dish dishAdd = (Dish) object;
        try {
            Store.getInstance().addDish(dishAdd);
        } catch (IngredientDoesNotExistException e) {
            System.out.println("?? ??????????, ?????????????? ???? ???????????? ????????????????, ???????? ???????????????????????????? ????????????????????");
            return new Message(CommandEnum.NOT_OK, null);
        } catch (DuplicateFoundException e) {
            System.out.println("??????????????????  ????????????????");
            return new Message(CommandEnum.ADDING_A_DUPLICATE_DISH, dishAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(CommandEnum.OK, null);
    }

    private Message addIngredient(Message message) {
        Object object = message.getObj();
        Ingredient ingredientAdd = (Ingredient) object;
        try {
            Store.getInstance().addIngredient(ingredientAdd);
        } catch (DuplicateFoundException e) {
            System.err.println("?????????????????? ????????????????");
            return new Message(CommandEnum.ADDING_A_DUPLICATE_INGREDIENT, ingredientAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(CommandEnum.OK, null);
    }


    private Message editDish(Message message) throws IOException {
        Object object = message.getObj();
        Dish dishEdit = (Dish) object;
        if (Store.getInstance().isExistDish(dishEdit)) {
            Store.getInstance().editDish(dishEdit);
            return new Message(CommandEnum.OK, null);
        }
        return new Message(CommandEnum.NOT_OK, null);
    }

    private Message editIngredient(Message message) throws IOException {
        Object object = message.getObj();
        Ingredient ingredientEdit = (Ingredient) object;
        System.out.println("ID ???????????????????????????? ??????????????????????: " + ingredientEdit.getId());
        if (Store.getInstance().isExistIngredient(ingredientEdit)) {
            Store.getInstance().editIngredient(ingredientEdit);
            return new Message(CommandEnum.OK, null);
        }
        return new Message(CommandEnum.NOT_OK, null);

    }

    private Message removeDish(Message message) throws IOException {
        Object object = message.getObj();
        Dish dishRemove = (Dish) object;
        Store.getInstance().removeDish(dishRemove);
        return new Message(CommandEnum.OK, null);
    }

    private Message removeIngredient(Message message) throws IOException {
        Object object = message.getObj();
        Ingredient ingredient = (Ingredient) object;
        Store.getInstance().removeIngredient(ingredient);
        return new Message(CommandEnum.OK, null);
    }

    private Message uploadToFile(Message message) {
        File fileDishes = new File("LibraryOfDishes/" + Constants.fileNameDishes);
        File fileIngredients = new File("LibraryOfIngredients/" + Constants.fileNameIngredients);
        try (BufferedOutputStream outD = new BufferedOutputStream(new FileOutputStream(fileDishes)); BufferedOutputStream outI = new BufferedOutputStream(new FileOutputStream(fileIngredients))) {
            int i = 0;
            ArrayList<Dish> dishes = new ArrayList<>();
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (Ingredient ingredient : Store.getInstance().getAllIngredients()) {
                System.out.println("???????????????????? " + ++i + ": " + ingredient.getName());
                ingredients.add(ingredient);
            }
            for (Dish dish : Store.getInstance().getAllDishes()) {
                System.out.println("?????????? " + ++i + ": " + dish.getName());
                dishes.add(dish);
            }

            RecipeIO.serializeDishDictionary(outD, dishes);
            RecipeIO.serializeIngredientDictionary(outI, ingredients);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(CommandEnum.OK, null);

    }

    private Message uploadFromFile(Message message) {
        File fileDishes = new File("LibraryOfDishes/" + Constants.fileNameDishes);
        File fileIngredients = new File("LibraryOfIngredients/" + Constants.fileNameIngredients);
        try (BufferedInputStream inD = new BufferedInputStream(new FileInputStream(fileDishes)); BufferedInputStream inI = new BufferedInputStream(new FileInputStream(fileIngredients))) {
            if (RecipeIO.isFileEmpty(fileDishes)) {
                return new Message(CommandEnum.NOT_OK, null);
            }
            ArrayList<Ingredient> deserializedIngredients = RecipeIO.deserializeIngredientDictionary(inI);
            for (Ingredient ingredient : deserializedIngredients) {
                if (!Store.getInstance().getAllIngredients().contains(ingredient)) {
                    Store.getInstance().addIngredient(ingredient);
                }
            }
            ArrayList<Dish> deserializedDishes = RecipeIO.deserializeDishDictionary(inD);
            for (Dish dish : deserializedDishes) {
                if (!Store.getInstance().getAllDishes().contains(dish)) {
                    Store.getInstance().addDish(dish);
                }
            }

            int i = 0;
            for (Dish dish : deserializedDishes) {
                System.out.println("?????????? " + ++i + ": " + dish.getName());
            }
            return new Message(CommandEnum.OK, null);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Message(CommandEnum.OK, null);
    }

}
