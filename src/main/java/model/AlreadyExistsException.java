package src.main.java.model;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(int accountID){
        super("An account with ID " + accountID + "already exists");
    }
    public AlreadyExistsException(String message){
        super(message);
    }
}
