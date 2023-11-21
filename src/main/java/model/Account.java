package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public class Account implements Serializable {

    private HashMap<Integer, Transaction> transactions;
    private double balance;
    private final int accountID;


    public Account(int accountID){
        this.transactions = new HashMap<Integer, Transaction>();
        this.balance = 0;
        this.accountID = accountID;
    }

    //Getters
    public double getBalance(){
        return this.balance;
    }
    public double getAccountID(){
        return this.accountID;
    }
    //Setters
    public void setBalance (double newBalance){
        this.balance = newBalance;
    }

    public String sendTransaction(Account toAccount, double amount){
        String message;
        if (balance < amount) {
            message = "Not enough currency available for this transaction";
        } else {
            toAccount.setBalance(toAccount.getBalance() + amount);
            setBalance(balance - amount);

            message = String.format(Locale.ENGLISH, "Transaction successfull. %d has been transfered.", amount);
        }
        return message;
    }


/*    public Transaction receiveTransaction(Account fromAccount, double amount){
        return new Transaction();
    }

 */

    public String withdraw( double amount){

        String message ="";
        if (amount > balance){
            message = "Not enough currency";
        } else {
            setBalance(balance - amount);
        }
        message = "Withdrawal successfull";
        return message;

    }

    public String toString() {
        return String.format("Account %f currently has %d in account balance.",accountID, balance);
    }

}
