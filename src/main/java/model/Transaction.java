package src.main.java.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.HashSet;
import java.util.UUID;

public class Transaction implements Serializable {
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private long sendingAccountNumber;
    private long receivingAccountNumber;
    public String type;
    private UUID transactionID;

    public Transaction(double transactionAmount, long receivingAccountNumber, long sendingAccountNumber, String type){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.sendingAccountNumber = sendingAccountNumber;
        this.receivingAccountNumber = receivingAccountNumber;
        this.type = type;
        this.transactionID = UUID.randomUUID();
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

    public UUID getTransactionID(){return this.transactionID;}

}
