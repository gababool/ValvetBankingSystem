package src.main.java.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    public StringProperty balanceProperty(){
        return new SimpleStringProperty(String.format("%.2f kr", getBalance()));
    }

    private void increaseBalance (double increaseAmount){
        this.balance = this.balance + increaseAmount;
    }

    private void decreaseBalance (double decreaseAmount) throws Exception{
        if (decreaseAmount > this.balance){
            throw new Exception("Insufficient funds");
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
    public LinkedHashMap<UUID, Transaction> getTransactions(){
        return  this.transactions;
    }

    public String toString () {
        return String.format("Account:  %s  |  Balance:  %.2f kr", this.accountNumber, this.balance);
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