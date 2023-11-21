package src.main.java.model;
import java.time.LocalDateTime;

public class Transaction {
    private final String transactionID;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private Account sendingAccount;
    private Account receivingAccount;

    public Transaction(String transactionID, double transactionAmount, Account sendingAccount, Account receivingAccount){
        this.transactionID = transactionID;
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
    }

    public String toString(){
        return this.transactionDate + ": " + this.transactionAmount + " kr was transferred from " + this.sendingAccount + " to " + this.receivingAccount;
    }

    public LocalDateTime getDate(){
        return LocalDateTime.now();
    }

    public double getAmount(){
        return this.transactionAmount;
    }


    public Account getSendingAccount(){
        return this.sendingAccount;
    }

    public Account getReceivingAccount(){
        return this.receivingAccount;
    }

}
