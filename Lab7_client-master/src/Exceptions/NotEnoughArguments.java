package Exceptions;

/**
 * Class of Exceptions in the situation of not enough arguments.
 */

public class NotEnoughArguments extends Exception{
    public NotEnoughArguments(String message){
        super(message);
    }
}