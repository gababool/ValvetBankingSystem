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

    private void increaseBalance(double increaseAmount) {
        this.balance += increaseAmount;
    }

    private void decreaseBalance(double decreaseAmount) {
        if ((this.balance -= decreaseAmount) < 0) {
            this.balance = 0;
        } else {
            this.balance -= decreaseAmount;
        }
    }

    public void sendTransaction(Transaction transaction) throws Exception {
        if (this.balance < transaction.getAmount()) {
            throw new Exception("Insufficient funds"); // REPLACE ME!
        } else {
            this.balance -= transaction.getAmount();
            this.transactions.put(transaction.getTransactionID(), transaction);
        }
    }

    public void receiveTransaction (Transaction transaction){
        this.balance += transaction.getAmount();
        this.transactions.put(transaction.getTransactionID(), transaction);
    }

    /*
    public String withdraw( double amount){

        String message = "";
        if (amount > this.balance) {
            message = "Not enough currency";
        } else {
            setBalance(this.balance - amount);
        }
        message = "Withdrawal successfull";
        return message;
    }
    */


    public String toString () {
        return String.format("Account %d currently has %.2f in account balance.", this.accountID, this.balance);
    }

}


