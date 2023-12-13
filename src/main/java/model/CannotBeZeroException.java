package src.main.java.model;

public class CannotBeZeroException extends Exception {

    // If a transaction is made while amount is 0, throws an exception
    // A customer can't have zero accounts
    public CannotBeZeroException(String message){
        super(message);
    }
}