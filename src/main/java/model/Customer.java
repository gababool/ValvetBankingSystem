package src.main.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;

public class Customer implements Serializable {

       private HashMap<Integer, Account> accounts;
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

    public String getSurname() {
        return this.surname;
    }

    public String getFirstName(){
           return this.firstName;
    }

    public int getPERSONAL_NUMBER(){
           return this.PERSONAL_NUMBER;
    }

    public HashMap<Integer, Account> getAccounts(){
           return this.accounts;
    }


    public void createAccount(int accountID) {
           Account newAccount = new Account(accountID);
        this.accounts.put(accountID, newAccount);
    }

    public void closeAccount(int accountID) throws Exception {
           if (this.accounts.get(accountID) == null) throw new Exception("The account you are trying to remove does not exist!");
           this.accounts.remove(accountID);
    }

    public String viewAllAccounts(){
        String lineSeparator = System.lineSeparator();
        String allAccounts = "Accounts for " + this.firstName + " " + this.surname + lineSeparator;
        for (Account account : this.accounts.values()){
            allAccounts += account.toString() + lineSeparator;
        }
            return allAccounts;
       }


       public String viewAccount(int accountID){
        Account account = this.accounts.get(accountID);
        if (account != null) {
            return account.toString();
        } else {
            return "Account not found";
        }
       }


       public double getTotalBalance(){
           double totalBalance = 0;

           for (Account account : this.accounts.values()){
               totalBalance += account.getBalance();
           }
           return totalBalance;
       }






}
