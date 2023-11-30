package src.main.java;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) throws Exception {
        // 1. Load Valvet, customers, accounts and transactions from data.json
        // Create a bank with customers, accounts, and transactions
        Valvet valv = new Valvet("1337");
        // Populate your bank with data...
        valv.createCustomer("Hans", "Bertil", 123);
        valv.createCustomer("Bertil", "Hans", 321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(321);
        valv.createAccount(123);
        valv.createAccount(123);
        valv.createAccount(123);
        valv.createAccount(123);
        valv.createAccount(123);
        valv.createAccount(123);


        valv.viewAllCustomers();
        // Save the bank data to a JSON file
        ValvetFileManager.saveBank(valv);

        // Load the bank data from the JSON file
        Valvet loadedBank = ValvetFileManager.loadBank();

        loadedBank.viewAllCustomers();
        System.out.println(valv.getCustomer(123).viewAllAccounts());
        System.out.println(valv.getCustomer(321).viewAllAccounts());

        // Display the loaded bank data
        if (loadedBank != null) {
            System.out.println(loadedBank);

        }


    }

}

