package src.main.java.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.HashSet;

public class Transaction implements Serializable {
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private long sendingAccountNumber;
    private long receivingAccountNumber;
    private int transactionID;

    public String type;

    public Transaction(double transactionAmount, Account recievingAccount, Account currentAccount, String type){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.currentAccount = currentAccount;
        this.recievingAccount = recievingAccount;
        this.type = type;
    }

    public String toString(){
        return this.transactionDate + ": " + this.transactionAmount + " kr was transferred from " + this.sendingAccountNumber + " to " + this.receivingAccountNumber;
    }

    public LocalDateTime getDate(){
        return LocalDateTime.now();
    }

    public double getAmount(){
        return this.transactionAmount;
    }

    public int getTransactionID(){return transactionID;}

}
