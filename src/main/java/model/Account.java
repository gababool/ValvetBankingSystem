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

    // Returns the balance as a string
    public StringProperty balanceProperty(){
        return new SimpleStringProperty(String.format("%.2f kr", getBalance()));
    }

    // Increases balance
    private void increaseBalance (double increaseAmount){
        this.balance = this.balance + increaseAmount;
    }

    // If statement checks if there are enough funds, if there are, throws exception
    private void decreaseBalance (double decreaseAmount) throws Exception{
        if (decreaseAmount > this.balance){
            throw new Exception("Insufficient funds");
        }
        this.balance = this.balance - decreaseAmount;
    }

    // Adds the transaction to the hashmap containing every transaction, then decreases balance of the sender's account.
    public void sendTransaction(Transaction transaction) throws Exception {
        transactions.put(transaction.getTransactionID(), transaction);
        this.decreaseBalance(transaction.getAmount());
    }

    // Logic for receiving a transaction, receives an object transaction containing information about amount etc, also adds the transaction to hashmap.
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

    // Equals method
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

    // Hashcode method
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, transactions);
    }
}