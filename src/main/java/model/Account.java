package src.main.java.model;

import java.util.HashMap;
import java.util.Locale;

public class Account {

    private HashMap<Transaction, Integer> transactions;
    private double balance;
    private final int accountID;


    public Account(int accountID){
        this.transactions = new HashMap<Transaction, Integer>();
        this.balance = 0;
        this.accountID = accountID;
    }

    //Getters
    public double getAccountBalance(){
        return this.balance;
    }
    public double getAccountID(){
        return this.accountID;
    }
    //Setters
    public void setBalance (double newBalance){
        this.balance = newBalance;
    }

    public String sendTransaction(Account toAccount, double amount){
        String message;
        if (this.getAccountBalance() < amount) {
            message = "Not enough currency available for this transaction";
        } else {
            double toAccountOldBalance = toAccount.getAccountBalance();
            toAccount.setBalance(toAccountOldBalance + amount);

            double fromAccountOldBalance = this.getAccountBalance();
            this.setBalance(fromAccountOldBalance - amount);

            message = String.format(Locale.ENGLISH, "Transaction successfull. %d has been transfered.", amount);
        }
        return message;
    }


    public Transaction receiveTransaction(Account fromAccount, double amount){
        return new Transaction();
    }

    public Transaction withdraw( Account fromAccount, double amount){

    }

    public String toString() {
        return String.format("Account %f currently has %d in account balance.",accountID, balance);
    }

}
