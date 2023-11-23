package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public class Account implements Serializable {

    private HashMap<Integer, Transaction> transactions;
    private double balance;
    private long accountID;

    public Account(long accountID) {
        this.transactions = new HashMap<>();
        this.balance = 0;
        this.accountID = accountID;
    }

    public Account() {}

    //Getters
    public double getBalance() {
        return this.balance;
    }

    public long getAccountID() {
        return this.accountID;
    }

    //Setters

    public void increaseBalance (double increaseAmount){
        this.balance = this.balance + increaseAmount;
    }

    private void decreaseBalance (double decreaseAmount) throws Exception{
        if (decreaseAmount > this.balance){
            throw new Exception("Unable to perform action");
        }
        this.balance = this.balance - decreaseAmount;
    }

    private int createTransactionId() {
        return this.transactions.size();
    }

    private void addTransactionToHistory(Transaction transaction) {
        int transactionId = this.createTransactionId();
        this.transactions.put(transactionId, transaction);
    }

    public void sendTransaction(Account receivingAccount, double amount) throws Exception {
        Transaction transaction = new Transaction(amount, receivingAccount, this, "send");
        addTransactionToHistory(transaction);
        this.decreaseBalance(amount);
    }

    public void receiveTransaction(Account currentAccount, double amount) {
        Transaction transaction = new Transaction(amount, this, currentAccount, "receive");
        addTransactionToHistory(transaction);
        this.increaseBalance(amount);
    }


    public void withdraw (double amount) throws Exception {
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


