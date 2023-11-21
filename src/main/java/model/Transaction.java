package src.main.java.model;
import java.time.LocalDateTime;

public class Transaction {
    private String transactionID;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private Account sendingAccount;
    private Account receivingAccount;

    public Transaction(double transactionAmount, Account sendingAccount, Account receivingAccount){
        this.transactionDate = LocalDateTime.now();
        this.transactionAmount = transactionAmount;
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
    }

    public String toString(){
        return ;
    }

    public LocalDateTime getDate(){
        return LocalDateTime.now();
    }

    public double getAmount(){
        return transactionAmount;
    }


    public Account getSendingAccount(){
        return sendingAccount;
    }

    public Account getReceivingAccount(){
        return receivingAccount;
    }



}
