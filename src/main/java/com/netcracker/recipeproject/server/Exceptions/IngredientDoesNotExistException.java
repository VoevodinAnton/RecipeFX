package com.netcracker.recipeproject.server.Exceptions;

public class IngredientDoesNotExistException extends RuntimeException{

    private static final long serialVersionUID = -53979087844731494L;

    public IngredientDoesNotExistException(){

    }

    public IngredientDoesNotExistException(String message){
        super(message);
    }
}
