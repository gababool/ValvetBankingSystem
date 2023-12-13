package src.main.java.model;

public class InvalidInputException extends Exception {

    // Exception for when an input you enter isnt valid (IE. You try to enter a letter in your personal number)

    public InvalidInputException(String message){
        super(message);
    }
}
