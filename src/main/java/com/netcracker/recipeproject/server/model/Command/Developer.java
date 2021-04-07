package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Message;

public class Developer {
   Command search;
   Command outputOfAllDishes;
   Command editDishCommand;
   Command addDishCommand;
   Command removeDishCommand;
   Command removeIngredientCommand;
   Command addIngredientCommand;
   Command outputOfAllIngredients;



    public Developer(Command search,
                     Command outputOfAllDishes,
                     Command editDishCommand,
                     Command addDishCommand,
                     Command removeDishCommand,
                     Command removeIngredientCommand,
                     Command addIngredientCommand,
                     Command outputOfAllIngredients) {
        this.search = search;
        this.outputOfAllDishes = outputOfAllDishes;
        this.editDishCommand = editDishCommand;
        this.addDishCommand = addDishCommand;
        this.removeDishCommand = removeDishCommand;
        this.removeIngredientCommand = removeIngredientCommand;
        this.addIngredientCommand = addIngredientCommand;
        this.outputOfAllIngredients = outputOfAllIngredients;
    }

    public Message searchDish(Message message){
        return search.execute(message);
    }

    public Message dishesOutput(Message message){
        return outputOfAllDishes.execute(message);
    }

    public Message editDish(Message message){
        return editDishCommand.execute(message);
    }

    public Message addDish(Message message){
        return addDishCommand.execute(message);
    }

    public Message removeDish(Message message){return removeDishCommand.execute(message);}

    public Message removeIngredient(Message message){return removeIngredientCommand.execute(message);}

    public Message addIngredient(Message message){
        return addIngredientCommand.execute(message);
    }

    public Message ingredientsOutput(Message message){return outputOfAllIngredients.execute(message);}

}
