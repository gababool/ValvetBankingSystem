package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;

public class Account implements Serializable {

    private HashMap<Integer, Transaction> transactions;
    private double balance;
    private final int accountID;


    public Account(int accountID) {
        this.transactions = new HashMap<Integer, Transaction>();
        this.balance = 0;
        this.accountID = accountID;
    }

    //Getters
    public double getBalance() {
        return this.balance;
    }

    public double getAccountID() {
        return this.accountID;
    }

    //Setters

    private void increaseBalance (double increaseAmount){
        this.balance += increaseAmount;
    }

    private void decreaseBalance (double decreaseAmount) {
        if ((this.balance -= decreaseAmount) < 0) {
            this.balance = 0;
        } else {
            this.balance -= decreaseAmount;
        }
    }

    private int createTransactionId() {
        return this.transactions.size();
    }

    private void addTransactionToHistory(Transaction transaction) {
        int transactionId = this.createTransactionId();
        this.transactions.put(transactionId, transaction);
    }

    public String sendTransaction(Account toAccount, double amount) {
        String message;

            if (this.balance < amount) {
                message = "Not enough currency available for this transaction";
            } else {
                toAccount.increaseBalance(amount);
                decreaseBalance(amount);

                message = String.format(Locale.ENGLISH, "Transaction successfull. %d has been transferred.", amount);
            }
            return message;
        }

/*
    public Transaction receiveTransaction(Account fromAccount, double amount){
=======

/*    public Transaction receiveTransaction(Account fromAccount, double amount){
>>>>>>> 67d1e367daad071861f1a5c899422211a99033a3
        return new Transaction();
    }

 */
    public void withdraw (double amount){
        this.decreaseBalance(amount);
        Transaction transaction = new Transaction(amount, this, "withdraw");
        addTransactionToHistory(transaction);
    }

    public void deposit (double amount){
        this.increaseBalance(amount);
        Transaction transaction = new Transaction(amount, this, "deposit");
        addTransactionToHistory(transaction);
    }


    public String toString () {
        return String.format("Account %f currently has %d in account balance.", this.accountID, this.balance);
    }

}

