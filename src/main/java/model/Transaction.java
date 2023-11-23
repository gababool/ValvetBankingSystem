package src.main.java.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    public LocalDateTime transactionDate;
    public double transactionAmount;
    public Account recievingAccount;
    public Account currentAccount;

    public String type;

    public Transaction(double transactionAmount, Account recievingAccount, String type){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.recievingAccount = recievingAccount;
        this.type = type;
    }

    public Transaction(double transactionAmount, Account recievingAccount, Account currentAccount, String type){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.currentAccount = currentAccount;
        this.recievingAccount = recievingAccount;
        this.type = type;
    }

    public String toString(){
        return this.transactionDate + ": " + this.transactionAmount + " kr was transferred from " + this.toAccount + " to " + this.fromAccount;
    }
}
