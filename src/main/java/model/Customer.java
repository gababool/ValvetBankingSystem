package src.main.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.SimpleTimeZone;

public class Customer implements Serializable {

       private LinkedHashMap<String, Account> accounts;
       private String firstName;
       private String surname;
       private String PERSONAL_NUMBER;


       public Customer(String firstName, String surname, String PERSONAL_NUMBER){
           this.PERSONAL_NUMBER = PERSONAL_NUMBER;
           this.firstName = firstName;
           this.surname = surname;
           this.accounts = new LinkedHashMap<>();
       }
       public Customer(){}

    @Override
    public String toString(){
         return String.format("Name: %s | PNO: %s | Accounts: %d | Total Balance: %.2f",
                 getFullName(), this.getPERSONAL_NUMBER(), getNumberOfAccounts(), getTotalBalance());
    }
    public final StringProperty accountProperty(){
            StringProperty accounts = new SimpleStringProperty((String)""+getNumberOfAccounts());
           return accounts;
    }
    public final StringProperty nameProperty(){
        StringProperty name = new SimpleStringProperty(getFullName());
        return name;
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

    public String getFullName(){ return this.firstName + " " + this.surname;}

    public String getPERSONAL_NUMBER(){
           return this.PERSONAL_NUMBER;
    }

    public LinkedHashMap<String, Account> getAccounts(){
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

       public String viewAccount(String accountNumber){
        Account account = this.accounts.get(accountNumber);
        if (account != null) {
            return account.toString();
        } else {
            return "Account not found";
        }
       }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Customer)) {
            return false;
        }
        Customer otherCustomer = (Customer) obj;
        boolean equalPersonNumber = this.PERSONAL_NUMBER == otherCustomer.getPERSONAL_NUMBER();
        boolean equalFirstName = Objects.equals(this.firstName, otherCustomer.getFirstName());
        boolean equalSurname = Objects.equals(this.surname, otherCustomer.getSurname());
        return equalPersonNumber && equalFirstName && equalSurname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PERSONAL_NUMBER, firstName, surname);
    }







}
