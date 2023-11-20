package src.main.java.model;

import java.util.HashMap;

public class Account {

<<<<<<< HEAD
    private HashMap<Transaction, Integer> transactions;
    private double balance;
    private final int accountID;


    public Account(int accountID){
        this.transactions = new HashMap<Transaction, Integer>();
        this.balance = 0;
        this.accountID = accountID;
    }

    public Transaction sendTransaction(Account toAccount){
        return new Transaction();
    }

    public Transaction receiveTransaction(Account fromAccount){
        return new Transaction();
    }

}
