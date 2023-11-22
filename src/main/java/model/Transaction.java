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

    public Transaction(int transactionID, double transactionAmount, long sendingAccountNumber, long receivingAccountNumber){
        this.transactionID = transactionID;
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.sendingAccountNumber = sendingAccountNumber;
        this.receivingAccountNumber = receivingAccountNumber;
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
