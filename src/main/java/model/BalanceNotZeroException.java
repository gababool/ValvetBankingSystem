package src.main.java.model;

public class BalanceNotZeroException extends Exception {

    public BalanceNotZeroException(String message){
        super(message);
    }
}
