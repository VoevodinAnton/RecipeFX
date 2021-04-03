package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Message;

public class Developer {
   Command search;
   Command outputOfAllDishes;
   Command editDishCommand;
   Command addDishCommand;

    public Developer(Command search, Command outputOfAllDishes, Command editDishCommand, Command addDishCommand) {
        this.search = search;
        this.outputOfAllDishes = outputOfAllDishes;
        this.editDishCommand = editDishCommand;
        this.addDishCommand = addDishCommand;
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
}
