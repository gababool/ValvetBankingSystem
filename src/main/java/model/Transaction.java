package src.main.java.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    public LocalDateTime transactionDate;
    public double transactionAmount;
    public Account toAccount;
    public Account fromAccount;

    public String type;

    public Transaction(double transactionAmount, Account toAccount, String type){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.toAccount = toAccount;
        this.type = type;
    }

    public Transaction(double transactionAmount, Account toAccount, Account fromAccount, String type){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.type = type;
    }

    public String toString(){
        return this.transactionDate + ": " + this.transactionAmount + " kr was transferred from " + this.toAccount + " to " + this.fromAccount;
    }
}
