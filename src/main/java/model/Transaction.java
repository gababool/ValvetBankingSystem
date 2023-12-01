package src.main.java.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.HashSet;
import java.util.UUID;

public class Transaction implements Serializable {
    private String transactionDate;
    private double transactionAmount;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private UUID transactionID;

    public Transaction(double transactionAmount, String receiverAccountNumber, String senderAccountNumber){
        this.transactionDate = LocalDateTime.now().toString();
        this.transactionAmount = transactionAmount;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transactionID = UUID.randomUUID();
    }
    public Transaction(){}

    public String toString(){
        return this.transactionDate + ": " + this.transactionAmount + " kr was transferred from " + this.senderAccountNumber + " to " + this.receiverAccountNumber;
    }

    public String getDate(){
        return this.transactionDate;
    }

    public double getAmount(){
        return this.transactionAmount;
    }

    public UUID getTransactionID(){
        return this.transactionID;
    }

}
