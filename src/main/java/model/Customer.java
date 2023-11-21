package src.main.java.model;

import java.util.HashMap;

public class Customer {

       private HashMap<Integer, Account> accounts;
       private String firstName;
       private String surname;
       private final int PERSONAL_NUMBER;


       public Customer(String firstName, String surname, int PERSONAL_NUMBER){
           this.PERSONAL_NUMBER = PERSONAL_NUMBER;
           this.firstName = firstName;
           this.surname = surname;
           this.accounts = new HashMap<>();
       }
    @Override
    public String toString(){
         return "Name: " + firstName + ", Surname: "  + surname + ", PNO: " + PERSONAL_NUMBER;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName(){
           return firstName;
    }

    public int getPERSONAL_NUMBER(){
           return PERSONAL_NUMBER;
    }

    public HashMap<Integer, Account> getAccounts(){
           return accounts;
    }


    public void createAccount(int accountID) {
           Account newAccount = new Account(accountID);
        accounts.put(accountID, newAccount);
    }

    public void closeAccount(int accountID) throws Exception {
           if (accounts.get(accountID) == null) throw new Exception("The account you are trying to remove does not exist!");
           accounts.remove(accountID);
    }

    public String viewAllAccounts(){
        String lineSeparator = System.lineSeparator();
        String allAccounts = "Accounts for " + firstName + " " + surname + lineSeparator;
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


       public double getTotalBalance(){
           double totalBalance = 0;

           for (Account account : accounts.values()){
               totalBalance += account.getBalance();
           }
           return totalBalance;
       }






}
