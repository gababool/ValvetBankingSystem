package src.main.java.model;

import javafx.scene.input.TransferMode;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.UUID;

public class Account implements Serializable {

    private LinkedHashMap<UUID, Transaction> transactions;
    private double balance;
    private String accountNumber;

    public Account(String accountNumber) {
        this.transactions = new LinkedHashMap<>();
        this.balance = 0;
        this.accountNumber = accountNumber;
    }

    public Account() {}

    //Getters

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    private void increaseBalance (double increaseAmount){
        this.balance = this.balance + increaseAmount;
    }

    private void decreaseBalance (double decreaseAmount) throws Exception{
        if (decreaseAmount > this.balance){
            throw new Exception("Unable to perform action");
        }
        this.balance = this.balance - decreaseAmount;
    }

    public void sendTransaction(Transaction transaction) throws Exception {
        transactions.put(transaction.getTransactionID(), transaction);
        this.decreaseBalance(transaction.getAmount());
    }

    public void receiveTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionID(), transaction);
        this.increaseBalance(transaction.getAmount());
    }

    // ROUND OFF ACCOUNT BALANCE TO 2 DECIMALS AND CHANGE TESTS ACCORDINGLY
    public String toString () {
        return String.format("Account %s: Balance %f kr", this.accountNumber, this.balance);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || !(obj instanceof Account)){
            return false;
        }
        Account otherAccount = (Account) obj;
        boolean equalAccountNumber = this.accountNumber == otherAccount.getAccountNumber();
        boolean equalBalance = this.balance == otherAccount.getBalance();
        boolean equalTransactions = this.transactions.values().equals(otherAccount.transactions.values());

        return equalAccountNumber && equalBalance && equalTransactions;
    }
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, transactions);
    }
}