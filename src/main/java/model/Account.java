package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public class Account implements Serializable {

    private HashMap<Integer, Transaction> transactions;
    private double balance;
    private int accountID;

    public Account(int accountID){
        this.transactions = new HashMap<>();
        this.balance = 0;
        this.accountID = accountID;
    }
    public Account(){};
    //Getters
    public double getBalance() {
        return this.balance;
    }

    public double getAccountID() {
        return this.accountID;
    }

    //Setters
    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void increaseBalance (double increaseAmount){
        this.balance += increaseAmount;
    }

    public void decreaseBalance (double decreaseAmount) {
        if ((this.balance -= decreaseAmount) < 0) {
            this.balance = 0;
        } else {
            this.balance -= decreaseAmount;
        }
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
        public String withdraw ( double amount){

            String message = "";
            if (amount > this.balance) {
                message = "Not enough currency";
            } else {
                setBalance(this.balance - amount);
            }
            message = "Withdrawal successfull";
            return message;

        }

        public String toString () {
            return String.format("Account %d currently has %f in account balance.", this.accountID, this.balance);
        }

    }

