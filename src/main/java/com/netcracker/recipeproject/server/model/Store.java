package com.netcracker.recipeproject.server.model;

import com.netcracker.recipeproject.library.Dish;
import com.netcracker.recipeproject.library.DishComponent;
import com.netcracker.recipeproject.library.Ingredient;
import com.netcracker.recipeproject.library.Message;
import com.netcracker.recipeproject.server.Exceptions.DuplicateFoundException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Store {
    DishDictionary dishDictionary;
    IngredientDictionary ingredientDictionary;

    public Store() {
        dishDictionary = new DishDictionary();
        ingredientDictionary = new IngredientDictionary();


        /////////////////////////////////
        Ingredient egg = new Ingredient("яйцо", "шт");
        Ingredient sausage = new Ingredient("колбаса", "гр");
        ingredientDictionary.add(egg);
        ingredientDictionary.add(sausage);
        DishComponent sausage1 = new DishComponent(sausage, 1);
        DishComponent egg3 = new DishComponent(egg, 3);
        ArrayList<DishComponent> ingredientsOfOmelette = new ArrayList<>();
        ingredientsOfOmelette.add(sausage1);
        ingredientsOfOmelette.add(egg3);
        Dish omelette = new Dish(ingredientsOfOmelette, "omelette", "10 минут");
        dishDictionary.addDish(omelette);
        //////////////////////////////
    }


    public void doCommand(Message message, ObjectOutputStream objOut) { //перенести в другой класс, в модель


        int flag = message.getFlag();
        Object object = message.getObj();

        switch (flag) { //сделать ENUM
            case 0: //Search
                System.out.println("получено молоко");
                String searchString = (String) object;

                System.out.println(searchString);
                ArrayList<Dish> dishesToClient = new ArrayList<>(); //список блюд, который отправляется клиенту

                ArrayList<Dish> dishes = dishDictionary.getDishes();
                for (Dish dish : dishes) {
                    if (dish.contains(searchString)) {
                        dishesToClient.add(dish);
                    }
                }
                try {
                    Message messageSearch = new Message(0, dishesToClient);
                    objOut.writeObject(messageSearch);
                    objOut.flush();
                    System.out.println(
                            "Отправлен список всех блюд");
                } catch (Exception ex) {
                    System.err.println("Отправлено исключение 1");
                    //TODO: create exception
                }
                break;
            case 1: //output of all dishes
                try {
                    System.out.println("Размер списка блюд " + dishDictionary.getDishes().size());
                    Message m = new Message(0, dishDictionary.getDishes());
                    objOut.writeObject(m);
                    objOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                Dish dishEdit = (Dish) object;
                dishDictionary.setDish(dishEdit);
                break;

            case 3:
                Dish dishAdd = (Dish) object;
                dishDictionary.addDish(dishAdd);
                break;
            case 6:
                Ingredient ingredientAdd = (Ingredient) object;
                try {
                    ingredientDictionary.add(ingredientAdd);
                } catch (DuplicateFoundException e) {
                    System.err.println("Обнаружен дупликат");
                    Message duplicateMessage = new Message(3, ingredientAdd);
                    try {
                        objOut.writeObject(duplicateMessage);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }

                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

        }

    }
}
