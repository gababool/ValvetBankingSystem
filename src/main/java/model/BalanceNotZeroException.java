package src.main.java.model;

public class BalanceNotZeroException extends Exception {

    // Can't remove customer if they currently have funds on any account.
    public BalanceNotZeroException(String message){
        super(message);
    }
}
