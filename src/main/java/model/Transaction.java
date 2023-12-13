package src.main.java.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction implements Serializable {
    private String transactionDate;
    private double transactionAmount;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private UUID transactionID;
    private String lineSeparator = System.lineSeparator();

    // Constructor
    public Transaction(double transactionAmount, String receiverAccountNumber, String senderAccountNumber){
        this.transactionDate = LocalDateTime.now().toString();
        this.transactionAmount = transactionAmount;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transactionID = UUID.randomUUID();
    }
    public Transaction(){}

    // ToString
    public String toString(){
        return "Date: " + this.transactionDate.substring(0,10) + lineSeparator
                + "Amount " + this.transactionAmount + " kr" + lineSeparator
        + "Sender: " + this.senderAccountNumber + lineSeparator
        + "Receiver: "+ this.receiverAccountNumber + lineSeparator
        + "Transaction ID: " + this.transactionID;
    }

    // Methods for the GUI integration
    public StringProperty transactionAmountProperty(){
        return new SimpleStringProperty(String.format("%.1f kr", getAmount()));
    }
    public StringProperty senderAccountNumberProperty(){
        return new SimpleStringProperty(senderAccountNumber);
    }
    public StringProperty receiverAccountNumberProperty(){
        return new SimpleStringProperty(receiverAccountNumber);
    }
    public StringProperty transactionDateProperty(){
        return new SimpleStringProperty(getDate().substring(0,10) + " " + getDate().substring(11,16));
    }

    // Getters
    public String getDate(){
        return this.transactionDate;
    }

    public double getAmount(){
        return this.transactionAmount;
    }

    public UUID getTransactionID(){
        return this.transactionID;
    }

    // Equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionID, that.transactionID);
    }

    // HashCode method
    @Override
    public int hashCode() {
        return Objects.hash(transactionID);
    }
}
