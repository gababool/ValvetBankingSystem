package src.main.java.model;

import java.util.HashMap;

public class Customer {

       private HashMap<Integer, Account> accounts;
       private String firstName;
       private String lastName;
       private final int PERSONAL_NUMBER;


       public Customer(String firstname, String lastName, int PERSONAL_NUMBER){
           this.PERSONAL_NUMBER = PERSONAL_NUMBER;
           this.firstName = firstname;
           this.lastName = lastName;
           this.accounts = new HashMap<>();

       }

    public String getLastName() {
        return lastName;
    }

    public String getfirstName(){
           return firstName;
    }

    public int getPERSONAL_NUMBER(){
           return PERSONAL_NUMBER;
    }

    public HashMap<Integer, Account> getAccounts(){
           return accounts;
    }

    public void createAccount(Account account) {
           accounts.put(account.getAccountID(), account);
    }
    public String viewAllAccounts(){
        String lineSeparator = System.lineSeparator();
        String allAccounts = "Accounts for " + firstName + " " + lastName + lineSeparator;
        for (Account account : accounts.values()){
            allAccounts += account.toString() + lineSeparator;
        }
            return allAccounts;
       }

       public String viewAccount(int accountID){
        Account account = accounts.get(accountID);
        if (account != null) {
            return account.toString();
        } else {
            return "Account not found";
        }
       }






}
