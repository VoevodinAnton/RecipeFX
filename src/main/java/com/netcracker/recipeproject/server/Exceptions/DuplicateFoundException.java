package com.netcracker.recipeproject.server.Exceptions;

public class DuplicateFoundException extends RuntimeException{
    private static final long serialVersionUID = 4237362048778768342L;
    public DuplicateFoundException(){}

    public DuplicateFoundException(String message){
        super(message);
    }
}
