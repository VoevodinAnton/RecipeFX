package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.CommandEnum;
import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;
import com.netcracker.recipeproject.server.IO.RecipeIO;
import com.netcracker.recipeproject.server.model.DishDictionary;
import com.netcracker.recipeproject.server.model.IngredientDictionary;
import com.netcracker.recipeproject.server.model.Store;

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
            case OUTPUT_OF_ALL_FILENAMES:
                return outputOfAllFileNames(message);
            case OPEN_A_FILE:
                return uploadFromFile(message);
            default:
                return null;
        }
    }

    private Message searchDish(Message message) throws IOException {
        Object object = message.getObj();
        String searchString = (String) object;

        ArrayList<Dish> dishesToClient = new ArrayList<>(); //список блюд, который отправляется клиенту

        for (Dish dish : DishDictionary.getInstance().getAllDishes()) {
            if (dish.contains(searchString)) {
                dishesToClient.add(dish);
            }
        }
        System.out.println(
                "Отправлен список всех блюд");
        return new Message(CommandEnum.SEARCH, dishesToClient);
    }

    private Message dishesOutput() throws IOException {
        int i = 0;
        ArrayList<Dish> dishes = new ArrayList<>();
        for (Dish dish : Store.getInstance().getAllDishes()) {
            System.out.println("Блюдо " + ++i + ": " + dish.getName());
            dishes.add(dish);
        }
        return new Message(CommandEnum.OUTPUT_OF_ALL_DISHES, dishes);
    }

    private Message ingredientsOutput() throws IOException {
        int j = 0;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : Store.getInstance().getAllIngredients()) {
            System.out.println("Ингредиент " + ++j + ": " + ingredient.getName());
            ingredients.add(ingredient);
        }
        return new Message(CommandEnum.OUTPUT_OF_ALL_INGREDIENTS, ingredients);
    }

    private Message addDish(Message message) {
        Object object = message.getObj();
        Dish dishAdd = (Dish) object;
        try {
            Store.getInstance().addDish(dishAdd);
        } catch (DuplicateFoundException e) {
            System.out.println("Обнаружен  дупликат");
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
            System.err.println("Обнаружен дупликат");
            return new Message(CommandEnum.ADDING_A_DUPLICATE_INGREDIENT, ingredientAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(CommandEnum.OK, null);
    }


    private Message editDish(Message message) throws IOException {
        Object object = message.getObj();
        Dish dishEdit = (Dish) object;
        Store.getInstance().editDish(dishEdit);
        return new Message(CommandEnum.OK, null);
    }

    private Message editIngredient(Message message) throws IOException {
        Object object = message.getObj();
        Ingredient ingredientEdit = (Ingredient) object;
        Store.getInstance().editIngredient(ingredientEdit);
        return new Message(CommandEnum.OK, null);
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
        Object object = message.getObj();
        String fileName = (String) object;
        System.out.println(fileName);
        File fileDishes = new File("LibraryOfDishes/" + fileName);
        File fileIngredients= new File("LibraryOfIngredients/" + "Ing" + fileName);
        try (BufferedOutputStream outD = new BufferedOutputStream(new FileOutputStream(fileDishes));BufferedOutputStream outI = new BufferedOutputStream(new FileOutputStream(fileIngredients)) ) {
            int i = 0;
            ArrayList<Dish> dishes = new ArrayList<>();
            ArrayList<Ingredient> ingredients  = new ArrayList<>();
            for (Dish dish : Store.getInstance().getAllDishes()) {
                System.out.println("Блюдо " + ++i + ": " + dish.getName());
                dishes.add(dish);
            }
            for (Ingredient ingredient : Store.getInstance().getAllIngredients()) {
                System.out.println("Блюдо " + ++i + ": " + ingredient.getName());
                ingredients.add(ingredient);
            }
            RecipeIO.serializeDishDictionary(outD, dishes);
            RecipeIO.serializeIngredientDictionary(outI, ingredients);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(CommandEnum.OK, null);
    }


    private Message outputOfAllFileNames(Message message){
        ArrayList<String> results = new ArrayList();
        File[] files = new File("LibraryOfDishes").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns nul.
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return new Message(CommandEnum.OUTPUT_OF_ALL_FILENAMES, results);
    }

    private Message uploadFromFile(Message message) throws IOException {
        Object object = message.getObj();
        String fileName = (String) object;
        File fileDishes = new File("LibraryOfDishes/" + fileName);
        File fileIngredients = new File("LibraryOfIngredients/" + "Ing" + fileName);

        if (fileDishes.exists() && fileIngredients.exists()){
            if(RecipeIO.isFileEmpty(fileDishes)){
                return new Message(CommandEnum.OK, null);
            } else{
                try (BufferedInputStream inD = new BufferedInputStream(new FileInputStream(fileDishes)); BufferedInputStream inI = new BufferedInputStream(new FileInputStream(fileIngredients))) {
                    ArrayList<Dish> deserializedDishes = RecipeIO.deserializeDishDictionary(inD);
                    for (Dish dish: deserializedDishes){
                        if (!Store.getInstance().getAllDishes().contains(dish)){
                            Store.getInstance().addDish(dish);
                        }
                    }
                    ArrayList<Ingredient> deserializedIngredients = RecipeIO.deserializeIngredientDictionary(inI);
                    for (Ingredient ingredient: deserializedIngredients){
                        if (!Store.getInstance().getAllIngredients().contains(ingredient)){
                            Store.getInstance().addIngredient(ingredient);
                        }
                    }
                    int i = 0;
                    for (Dish dish : deserializedDishes) {
                        System.out.println("Блюдо " + ++i + ": " + dish.getName());
                    }
                    return new Message(CommandEnum.OK, null);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } else {
            fileDishes.createNewFile();
            fileIngredients.createNewFile();
        }
        return new Message(CommandEnum.OK, null);
    }


}
