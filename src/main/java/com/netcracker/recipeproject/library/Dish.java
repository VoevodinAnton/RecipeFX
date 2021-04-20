package com.netcracker.recipeproject.library;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Dish implements Serializable, Comparable<Dish> {
    private static final long serialVersionUID = 5863963783465074543L;
    private int id;
    private ArrayList<DishComponent> listOfIngredients;
    private String name;
    private String cookingTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        Collections.sort(listOfIngredients);
        Collections.sort(dish.listOfIngredients);

        return listOfIngredients.equals(dish.listOfIngredients) && name.equalsIgnoreCase(dish.name) && cookingTime.equals(dish.cookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfIngredients, name, cookingTime);
    }

    public Dish(ArrayList<DishComponent> listOfIngredients, String name, String cookingTime) {
        this.listOfIngredients = listOfIngredients;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public Dish(int id, ArrayList<DishComponent> listOfIngredients, String name, String cookingTime) {
        this.id = id;
        this.listOfIngredients = listOfIngredients;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<DishComponent> getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(ArrayList<DishComponent> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }



    public ArrayList<String> nameOfIngredientsToArray() {
        ArrayList<String> listOfIngredient = new ArrayList();
        for (DishComponent dishComponent : this.listOfIngredients) {
            listOfIngredient.add(dishComponent.getIngredient().getName());
        }
        return listOfIngredient;
    }


    public boolean contains(String ingredientsSearch) {
        List<String> listOfIngredients = Arrays.asList(ingredientsSearch.split("\\s*,\\s*"));
        List<String> nameOfIngredientsDish = this.nameOfIngredientsToArray();
        boolean result = false;
        for(String item : listOfIngredients){
            item = item.toLowerCase();
            if(item.contains("*") || item.contains("?")) {
                item = item.replace("*", ".*");
                item = item.replace("?", ".?");
                Pattern pattern = Pattern.compile(item);
                for(String itemDish:nameOfIngredientsDish) {
                    Matcher matcher = pattern.matcher(itemDish.toLowerCase());
                    if (matcher.find()){
                        String str = itemDish.substring(matcher.start(), matcher.end());
                        if(itemDish.toLowerCase().equals(str))
                            result = true;
                    }
                }
            }
            else {
                item = item;
                for(String itemDish:nameOfIngredientsDish) {
                    if (item.equals(itemDish.toLowerCase())){
                        result = true;
                    }
                }
            }

        }

        return result;
    }


    public boolean contains(int idIngredient){
        for(DishComponent dishComponent: listOfIngredients){
            if(idIngredient == dishComponent.getIngredient().getId()){
                return true;
            }
        }
        return false;
    }

    public int findIngredient(int idIngredient){
        int i = 0;
        if(this.contains(idIngredient)){
            for (DishComponent dishComponent: listOfIngredients){
                if(idIngredient == dishComponent.getIngredient().getId()){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public int compareTo(Dish dish) {
        String[] parts1 = cookingTime.split(" ");
        Integer thisTime = Integer.parseInt(parts1[0]);
        String[] parts2 = cookingTime.split(" ");
        Integer anotherTime = Integer.parseInt(parts2[0]);
        return thisTime.compareTo(anotherTime);
    }
}
