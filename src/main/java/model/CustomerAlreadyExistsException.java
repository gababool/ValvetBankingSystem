package src.main.java.model;

import java.util.concurrent.ExecutionException;

public class CustomerAlreadyExistsException extends Exception {

    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
