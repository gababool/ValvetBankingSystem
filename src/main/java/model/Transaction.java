package src.main.java.model;
import java.time.LocalDateTime;
import java.util.Locale;

public class Transaction {
    private int transactionID;
    private LocalDateTime transactionDate;
    private double transactionAmount;
    private Account sendingAccount;
    private Account receivingAccount;

    public Transaction(double transactionAmount, Account sendingAccount, Account receivingAccount){
        this.transactionDate = LocalDateTime.now();
    }

    public String toString(){

    }

    public LocalDateTime getDate(){

    }

    public double getAmount(){

    }

    public Customer getSender(){

    }

    public Customer getReceiver(){

    }

    public Account getSendingAccount(){

    }

    public Account getReceivingAccount(){

    }


}
