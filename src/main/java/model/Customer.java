package src.main.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;

public class Customer implements Serializable {

       private HashMap<String, Account> accounts;
       private String firstName;
       private String surname;
       private int PERSONAL_NUMBER;


       public Customer(String firstName, String surname, int PERSONAL_NUMBER){
           this.PERSONAL_NUMBER = PERSONAL_NUMBER;
           this.firstName = firstName;
           this.surname = surname;
           this.accounts = new HashMap<>();
       }
       public Customer(){}

    @Override
    public String toString(){
         return "Name: " + this.firstName + ", Surname: "  + this.surname + ", PNO: " + this.PERSONAL_NUMBER;
    }

    //Getters
    public String getSurname() {
           return this.surname;
    }

    public Account getAccount(String accountNumber){
           return accounts.get(accountNumber);
    }

    public int getNumberOfAccounts(){
           return accounts.size();
    }

    public String getFirstName(){
           return this.firstName;
    }

    public int getPERSONAL_NUMBER(){
           return this.PERSONAL_NUMBER;
    }

    public HashMap<String, Account> getAccounts(){
           return this.accounts;
    }

    public double getTotalBalance(){
        double totalBalance = 0;

        for (Account account : this.accounts.values()){
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    //Setters
    public void setSurname(String surname){
           this.surname = surname;
    }

    public void setFirstName(String firstName) {
           this.firstName = firstName;
       }


    public Account createAccount(String accountID) {
        Account createdAccount = new Account(accountID);
        this.accounts.put(accountID, createdAccount);
        return createdAccount;
    }

    public void closeAccount(String accountNumber) throws Exception {
           if (this.accounts.get(accountNumber) == null) throw new Exception("The account you are trying to remove does not exist!");
           else {this.accounts.remove(accountNumber);}
    }

    public String viewAllAccounts(){
        String lineSeparator = System.lineSeparator();
        String allAccounts = "Accounts for " + this.firstName + " " + this.surname + lineSeparator;
        for (Account account : this.accounts.values()){
            allAccounts += account.toString() + lineSeparator;
        }
            return allAccounts;
       }


       public String viewAccount(String accountNumber){
        Account account = this.accounts.get(accountNumber);
        if (account != null) {
            return account.toString();
        } else {
            return "Account not found";
        }
       }











}
